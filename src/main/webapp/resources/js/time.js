$(function() {
	timeKeyboard.init();
});

var timeKeyboard = {
	
	init: function() {
		$('.keyboard .number').on('click', function() {
			$('.codeInput input').val($('.codeInput input').val() + $(this).text());
		});
		
		$('.keyboard .delete').on('click', function() {
			var code = $('.codeInput input').val();
			$('.codeInput input').val(code.substr(0, code.length - 1));
		});
	}	
};