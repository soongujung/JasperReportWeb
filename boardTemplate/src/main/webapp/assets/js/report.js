/**
 * 
 */

$(function(){
	var reports = {};
	var report;
	var viewer = $('.report-view');
	var container = ('.report-rows');
	var reportForm = $('.report-form form');

	function loadReports(){
		$.get('/scrapper/birt/report/list', function(result){
			reports = {};
			result.forEach(function(report){
				reports[report.name] = report;
				$('<div class="col-md-6"></div>').append(
					'<h3>' + report.title
							+ '(<a href="javascript:void(0);" data-report="'
							+ report.name + '">View Full Report</a>)</h3>')
					.append($(res).find('embed').parent().html()).appendTo(
							container);
			});
		});
	}

	// reload button event
	// report form 앨리먼트 내에 div,label, input등을 지정 후 preppend(element)
	$('container').on('click', 'a', function(){
		viewer.show();
		container.hide();
		report = reports[$(this).data('report')];

		viewer.find('h2 span').text(report.name);
		viewer.find('.content').html("");
		reportForm.find('div').remove();

		report.parameters.forEach(function(param){
			var element = $('<div class="form-group"></div>');
			element.append($('<label></label> ').text(param.title + ": "));
			element.append($(' <input type="text"/>').attr("name", param.name));
			reportForm.prepend(element);
		});
	});

	// input 태그에 지정된 각각의 name 어트리뷰트에 대한 값을 data[]에 key로 지정, value는 el.value
	reportForm.on('submit', function(evt){
		evt.preventDefault();
		var data = {};
		$(this).find('input').each(function(i, el){
			data[el.name] = el.value;
		});
		loadMainReport(data);
	});

	// report/main/{report.name}에 form POST 요청
	function loadMainReport(data){
		viewer.find('.content').html("");
		$.post('report/main/' + report.name, data, function(res){
			$(res).each(function(i, item){
				if(item.nodeName === 'STYLE'){
					$('head').append(item);
				}
				else if(item.nodeName === 'TABLE'){
					viewer.find('.content').append(item);
				}
			})
		})
	}

	// .report-view CLICK EVENT
	$(viewer).on('click', 'h2 a', function(){
		viewer.hide();
		container.show();
		$('head').find('style').remove();
	});

	// reload anchor CLICK EVENT
	$('.reload-link').click(function(){
		$('head').find('style').remove();
		container.hide();
		viewer.hide();
		container.html("");
		$.get('report/reload', function(){
			loadReports();
		});
	});

	loadReports();
})