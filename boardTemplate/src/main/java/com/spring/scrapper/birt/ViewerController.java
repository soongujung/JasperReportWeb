package com.spring.scrapper.birt;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.report.context.BirtContext;
import org.eclipse.birt.report.context.IContext;
import org.eclipse.birt.report.exception.ViewerException;
import org.eclipse.birt.report.presentation.aggregation.IFragment;
import org.eclipse.birt.report.presentation.aggregation.layout.FramesetFragment;
import org.eclipse.birt.report.presentation.aggregation.layout.RunFragment;
import org.eclipse.birt.report.resource.BirtResources;
import org.eclipse.birt.report.resource.ResourceConstants;
import org.eclipse.birt.report.service.BirtReportServiceFactory;
import org.eclipse.birt.report.service.BirtViewerReportService;
import org.eclipse.birt.report.session.IViewingSession;
import org.eclipse.birt.report.session.ViewingSessionUtil;
import org.eclipse.birt.report.utility.BirtUtility;
import org.eclipse.birt.report.utility.ParameterAccessor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping("/viewer")
public class ViewerController implements ApplicationContextAware{
	
	private ServletContext servletContext;
	private IFragment viewer = null;
	private IFragment run = null;
	
	@PostConstruct
	public void init(){
		ParameterAccessor.initParameters(servletContext);
		BirtResources.setLocale(ParameterAccessor.getWebAppLocale());
		BirtReportServiceFactory.init( new BirtViewerReportService(servletContext) );
		
		viewer = new FramesetFragment();
		viewer.buildComposite();
		viewer.setJSPRootPath("/birt"); //$NON-NLS-1$
		
		run = new RunFragment();
		run.buildComposite( );
		run.setJSPRootPath( "/birt" ); //$NON-NLS-1$
	}

	@Override
	public void setApplicationContext(ApplicationContext appContext) throws BeansException {
		servletContext = ((WebApplicationContext)appContext).getServletContext();
	}
	
	@RequestMapping(value = "/{viewerType}", method=RequestMethod.GET)
	public void getFrame(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable String viewerType, @RequestParam("__report")String reportFileName)
			throws ServletException, IOException{
		
		String resultPage = "";
		try {
			IViewingSession session = ViewingSessionUtil.createSession(request);
			session.lock();
			IFragment activeFragment = null;
			try
			{
				IContext context = getContext( request, response );
	
				if ( context.getBean( ).getException( ) != null )
				{
					handleNonSoapException( request, response, context.getBean( )
							.getException( ) );
				}
				else
				{
					response.setContentType("text/html;charset=utf-8");
					response.setHeader("Cache-Control","no-store");  //$NON-NLS-1$//$NON-NLS-2$
					response.setHeader("Pragma","no-cache");  //$NON-NLS-1$//$NON-NLS-2$
					response.setDateHeader("Expires", 0);    //$NON-NLS-1$
					
					request.setAttribute("fragment", activeFragment);
					
					if(viewerType.equalsIgnoreCase("frameset")){
//						activeFragment = viewer;
						resultPage = "/birt"+ "/pages/layout/" + "FramesetFragment";
					}
					else if(viewerType.equalsIgnoreCase("run")){
//						activeFragment = run;
						resultPage = "/birt"+ "/pages/layout/" + "RunFragment";
					}
					
//					if(activeFragment !=null){
//						activeFragment.service(request, response);
//					}
					
					RequestDispatcher rd = request.getRequestDispatcher(resultPage);
					rd.include(request, response);
				}
			} catch (ServletException | BirtException | IOException e) {
				e.printStackTrace();
			} 
			finally
			{
				session.unlock();
			}
		} catch (ViewerException e) {
			handleNonSoapException(request, response, e);
		}
	}
	
	@RequestMapping(value = "/{viewerType}", method=RequestMethod.POST)
	private void postFrame(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
//		http://localhost:8080/scrapper/viewer/frameset?__report=sample.rptdesign&__sessionId=20180713_130617_237&__dpi=96
		
//		Enumeration<String> parameterNames = request.getParameterNames();
//		while(parameterNames.hasMoreElements()){
//			String parameterName = parameterNames.nextElement();
//			String value = request.getParameter(parameterName);
//		}
		
		StringBuffer builder = new StringBuffer( );
		Iterator it = request.getParameterMap( ).keySet( ).iterator( );
		while ( it.hasNext( ) )
		{
			String paramName = (String) it.next( );
			if ( paramName != null && paramName.startsWith( "__" ) ) //$NON-NLS-1$
			{
				String paramValue = ParameterAccessor.urlEncode(
						ParameterAccessor.getParameter( request, paramName ),
						ParameterAccessor.UTF_8_ENCODE );
				builder.append( "&" + paramName + "=" + paramValue ); //$NON-NLS-1$//$NON-NLS-2$
			}
		}
		String soapURL = request.getRequestURL( ).toString( );
		builder.deleteCharAt( 0 );
		soapURL += "?" + builder.toString( ); //$NON-NLS-1$

		request.setAttribute( "SoapURL", soapURL ); //$NON-NLS-1$
		
		String requestType = request.getHeader("request-type");
		boolean isSoapRequest = "soap".equalsIgnoreCase(requestType);
		
		IViewingSession session = null; 
		IContext context = null;
		
		try
		{
			session = ViewingSessionUtil.getSession( request );
			if ( session == null && !isSoapRequest )
			{
				if ( ViewingSessionUtil.getSessionId(request) == null )
				{
					session = ViewingSessionUtil.createSession( request );
				}
				else
				{
					// if session id passed through the URL, it means this request
					// was expected to run using a session that has already expired 
					throw new ViewerException( BirtResources.getMessage(
							ResourceConstants.GENERAL_ERROR_NO_VIEWING_SESSION ) );
				}
			}
			context = getContext( request, response );
		}
		catch ( BirtException e )
		{
			// throw exception
			handleNonSoapException( request, response, e );
			return;
		}
		finally{
			if(session!=null)
				session.unlock();
		}
		
		
		
	}
	
	private IContext getContext(HttpServletRequest request, HttpServletResponse response) throws BirtException{
		BirtReportServiceFactory.getReportService().setContext( servletContext, null );
		return new BirtContext( request, response );
	}
	
	private void handleNonSoapException(HttpServletRequest request, HttpServletResponse response, Exception exception) throws ServletException, IOException{
		exception.printStackTrace();
		BirtUtility.appendErrorMessage(response.getOutputStream(), exception);
	}
}
