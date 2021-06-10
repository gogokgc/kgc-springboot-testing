var main = {
	init: function() {
		var _this = this;
		$('#btn-save').on('click', function() {
			_this.save();
		});

		$('#btn-update').on('click', function() {
			_this.update();
		});
		
		$('#btn-delete').on('click', function() {
			_this.delete();
		});
		
		$('#btn-check').on('click', function() {
			_this.check();
		});
	},

	save: function() {
		var data = {
			title: $('#title').val(),
			content: $('#content').val(),
			author: $('#author').val()
		};
		
		if(data.title.length != 0 && data.content.length != 0){
			
		$.ajax({
			type: 'POST',
			url: '/api/v1/posts',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			data: JSON.stringify(data)
		}).done(function() {
			alert('Posting Complete');
			window.location.href = '/';
		}).fail(function(error) {
			alert(JSON.stringify(error));
		})
		}else{
			alert('Please fill out title, content boxs');
			return false;
		}
	},

	update: function() {
		var data = {
			title: $('#title').val(),
			content: $('#content').val()
		};

		var id = $('#id').val();
		
		if(confirm('Are you sure edit this post?')){
		$.ajax({
			type: 'PUT',
			url: '/api/v1/posts/' + id,
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			data: JSON.stringify(data)
		}).done(function() {
			alert('Editing Complete');
			window.location.href = '/';
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
		}else{
			return false;
		}
	},
	
	delete: function() {
		var id = $('#id').val();
		
		if(confirm('Are you sure for Delete?')){
		$.ajax({
			type: 'DELETE',
			url: '/api/v1/posts/' + id,
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
		}).done(function() {
			alert('Delete Complete');
			window.location.href = '/';
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
		}else{
		return false;
		}
	},
	
	check: function() {
		var name = $('#userName').val();
		var author = $('#author').val();
		var id = $('#id').val();
		if(name == author){
			window.location.href = '/posts/update/' + id;
		}else{
			alert('you can not edit others posting');
		}
		
	},
	
};
main.init();