$(document).ready(function(){
		var dataTable = $('#tableTextList').DataTable({
							"bSort" : false,
							"bFilter": false,
							"columnDefs": [{
											"targets": [ 2 ],
				                			"visible": false
											}
							              ]
						});
		$('#tableTextList tbody').on('click', 'a', function(){
			var rowData = dataTable.row($(this).parents('tr')).data();
			$('#idTextId').val(rowData[0]);
			$('#idTextTitle').val(jQuery(rowData[1]).text());
			$('#idTextContent').val(rowData[2]);
			
			$('#idTextTitle').attr('readonly',true);
			
			$('.header-addtext').text('Edit Text');
			$('.btn-add-edit-text').html('Edit');
			$('.form-add-new-text').attr('action','update-text');
			$('.btn-add-new-text').show();
			$('.form-add-new-text').show(1000);
		});
		
		$('.div-title-already-exists').hide(5000);
		$('.div-text-added').hide(2000);
		$('.form-add-new-text').hide();
		
		$('.btn-add-new-text').on('click', function(){
			$('.header-addtext').text('Add Text');
			$('.btn-add-edit-text').html('Add');
			
			$('#idTextId').val('');
			$('#idTextTitle').val('');
			$('#idTextContent').val('');
			
			$('#idTextTitle').attr('readonly',false);
			$('.form-add-new-text').attr('action','add-text');
			
			$('.form-add-new-text').show(1000);
			if($('.btn-add-edit-text').html() == 'Add'){
				$(this).hide();
			}
		})
		
		
		
		$('.btn-add-edit-text').on('click', function(){
			$('.form-add-new-text div').each(function(){
				$(this).removeClass('has-error');
			});
			
			if($.trim($('#idTextTitle').val()).length == 0){
				$('#idTextTitle').closest('div').addClass('has-error');
				return false;
			}
			if($.trim($('#idTextContent').val()).length == 0){
				$('#idTextContent').closest('div').addClass('has-error');
				return false;
			}
		});
	});