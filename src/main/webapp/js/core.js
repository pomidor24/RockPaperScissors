String.prototype.format = function() {
    var formatted = this
    for (var i = 0; i < arguments.length; i++) {
        var regexp = new RegExp('\\{'+i+'\\}', 'gi')
        formatted = formatted.replace(regexp, arguments[i])
    }
    return formatted
}



var App = {}

var Cfg = {
	'url_prefix': 'RPS',
	'humanOpponentsSeq': ['shannon_human', 'shandom_human', 'random_human', 'learning_human'],
	'pcOpponentsSeq': ['shannon_pc', 'shandom_pc', 'random_pc', 'learning_pc'],
	
	'pcOpponents': {
		'shannon_pc': 	['shannon_1', 'shandom_1', 'random_1', 'learning_1'],
		'shandom_pc': 	['shannon_2', 'shandom_2', 'random_2', 'learning_2'],
		'random_pc': 	['shannon_3', 'shandom_3', 'random_3', 'learning_3'],
		'learning_pc':  ['shannon_4', 'shandom_4', 'random_4', 'learning_4']
	},
	
	'robotDescriptions': {
		'shandom': {
			'name': 'Shannon Frivolous',
			'description': 'Gives more chances to repeated sequences',
			'icon': 'vendors/glyphish/icons-gray/132-ghost@2x.png'		
		},
		'random': {
			'name': 'Random Guy',
			'description': 'Super Unpredictable',
			'icon': 'vendors/glyphish/icons-gray/276-poison@2x.png'		
		},
		'shannon': {
			'name': 'Shannon Strategist',
			'description': 'Remembers the sequences of choices',
			'icon': 'vendors/glyphish/icons-gray/201-vampire@2x.png'		
		},
		'learning': {
			'name': 'Promising Alien',
			'description': 'If loses, increases the weight of opponents decision',
			'icon': 'vendors/glyphish/icons-gray/202-alien@2x.png'			
		}
	}
}

App.func = {}

App.func.getRobotDesc = function(name) {
	var parts = name.split('_')
	var obj = Cfg.robotDescriptions[parts[0]]
	return obj;
}

App.req = {}

App.req.getScores = function(name1, name2, callback) {
	$.ajax({  
		type: "GET",  
		url: 'rest/scores/{0},{1}'.format(name1, name2),
		success: function(data, textStatus, jqXHR) { 
			callback(data)
		}, error: function(obj) {
			console.log(obj)
		}
	})
}

App.req.nextHuman = function(name, decision, callback) {
	$.ajax({  
		type: "GET",  
		url: 'rest/person/next/{0}/{1}'.format(name, decision),
		success: function(data, textStatus, jqXHR) { 
			callback(data)
			console.log(data)
		}
	})
}

App.req.nextRobot = function(opponent1, opponent2, callback) {
	$.ajax({  
		type: "GET",  
		url: 'rest/robot/next/{0},{1}'.format(opponent1, opponent2),
		success: function(data, textStatus, jqXHR) { 
			callback(data)
			console.log(data)
		}
	})
}


App.req.nullify = function(name1, name2, callback) {
	$.ajax({  
		type: "GET",  
		url: 'rest/nullify/{0},{1}'.format(name1, name2),
		success: function(data, textStatus, jqXHR) {
			App.req.getScores(name1, name2, callback)
		}
	})
}

App.html = {}

App.html.humanOpponent = function(id, name, description, icon) {
	var template = 
		'<li>'
			+'<a href="#game?opponent1=human&opponent2={0}" data-transition="flip">'
				+'<img src="{1}"/>'
				+'<h3>{2}</h3>'
				+'<p>{3}</p>'
			+'</a>'
		+'</li>'
		
	var str = template.format(id, icon, name, description)
	return str
}

App.html.pcFirstChoice = function(id, name, description, icon) {
	var template = 
		'<li id="{0}">'
			+'<a href="#" data-transition="flip">'
				+'<img src="{1}"/>'
				+'<h3>{2}</h3>'
				+'<p>{3}</p>'
			+'</a>'
		+'</li>'
		
	var str = template.format(id, icon, name, description)
	return str
}

App.html.pcSecondChoice = function(opponent1, opponent2, name, description, icon) {
	var template = 
		'<li>'
			+'<a href="#game?opponent1={0}&opponent2={1}" data-transition="flip">'
				+'<img src="{2}"/>'
				+'<h3>{3}</h3>'
				+'<p>{4}</p>'
			+'</a>'
		+'</li>'
		
	var str = template.format(opponent1, opponent2, icon, name, description)
	return str
}

App.view = {}

App.view.updateStats = function(data) {
	$('#first_score').html(data.firstWon)
	$('#second_score').html(data.secondWon)
	$('#draw_score').html(data.draw)
}

App.view.hideScores = function() {
	$('#scores').css('visibility', 'hidden')
}

App.view.showScores = function() {
	$('#scores').css('visibility', 'visible')
}

App.view.showResult = function(data) {
	if (data.won == 'first') {
		$('#first_choices img[data-app="'+data.first+'"] ').addClass('won')
		$('#second_choices img[data-app="'+data.second+'"] ').addClass('lost')
	} else if (data.won == 'second') {
		$('#first_choices img[data-app="'+data.first+'"] ').addClass('lost')
		$('#second_choices img[data-app="'+data.second+'"] ').addClass('won')				
	} else {
		$('#first_choices img[data-app="'+data.first+'"] ').addClass('draw')
		$('#second_choices img[data-app="'+data.second+'"] ').addClass('draw')					
	}
}

App.view.removeChoices = function() {
	$('#choices img').removeClass()
}

App.page = {}

App.page.initHumanList = function() {
	$(document).on('pageinit', '#person_vs_cp', function() {
		$('#human_opponents_list').html('')
		
		for (var i = 0; i < Cfg.humanOpponentsSeq.length; i++) {
			var name = Cfg.humanOpponentsSeq[i]
			var o = App.func.getRobotDesc(name)
			var str = App.html.humanOpponent(name, o.name, o.description, o.icon)
			$('#human_opponents_list').append(str)
		}
		
		$('#human_opponents_list').listview('refresh')
	})
}

App.page.initPcLists = function() {
	var opponent1
	
	function _selectItem(id) {
		$('#pc_opponents_list1 li').removeClass('ui-btn-active')
		$('#pc_opponents_list1 #'+id).addClass('ui-btn-active')
		opponent1 = id
		$('#pc_opponents_list1').listview('refresh')
		_showList2()
	}
	
	function _showList1() {
		$('#pc_opponents_list1').html('')
		for (var i = 0; i < Cfg.pcOpponentsSeq.length; i++) {
			var name = Cfg.pcOpponentsSeq[i]
			var o = App.func.getRobotDesc(name)
			var str = App.html.pcFirstChoice(name, o.name, o.description, o.icon)
			$('#pc_opponents_list1').append(str)
		}
		
		$('#pc_opponents_list1').listview('refresh')
	}
	
	function _showList2() {
		$('#pc_opponents_list2').html('')
		var list = Cfg.pcOpponents[opponent1]
		
		for (var i = 0; i < list.length; i++) {
			var name = list[i]
			var o = App.func.getRobotDesc(name)
			var str = App.html.pcSecondChoice(opponent1, name, o.name, o.description, o.icon)
			$('#pc_opponents_list2').append(str)
		}
		
		$('#pc_opponents_list2').listview('refresh')
	}

	$(document).on('pageinit', '#cp_vs_cp', function() {
		_showList1()
	})
	
	$(document).on('pagebeforeshow', '#cp_vs_cp', function() {
		_selectItem('shannon_pc')
	})
	
	$(document).on('click', '#pc_opponents_list1 li', function() {
		var id = $(this).attr('id')
		_selectItem(id)
	})
}

App.page.initGame = function() {
	var opponent1
	var opponent2
	
	var shuffling = false

	$(document).on('pagebeforeshow', '#game', function() {
		App.view.removeChoices()
		App.view.hideScores()
		
		$('#first_choices').removeClass('human_choices')
				
		opponent1 = $.mobile.pageData.opponent1
		opponent2 = $.mobile.pageData.opponent2
		
		if (!opponent1 || !opponent2) {
			$.mobile.changePage("#menu")
			return
		}

		var obj2 = App.func.getRobotDesc(opponent2)
		$('#second_player').html('<img src="{0}"/><br/>{1}'.format(obj2.icon, obj2.name))
		
		if (opponent1 == 'human') {
			$('#fight').closest('.ui-btn').hide()
			$('#first_choices').addClass('human_choices')
			$('#first_player').html('<img src="vendors/glyphish/icons-gray/253-person@2x.png"/><br/>You')
		} else {
			var obj1 = App.func.getRobotDesc(opponent1)
			$('#first_player').html('<img src="{0}"/><br/>{1}'.format(obj1.icon, obj1.name))
			$('#fight').closest('.ui-btn').show()
		}
		
		App.req.getScores(opponent1, opponent2, function(data) {
			App.view.updateStats(data)
			App.view.showScores()
		})
	})
	
	$('#choices img').jrumble({
		x: 7, y: 8, rotation: 8, speed: 15
	})
	
	$(document).on('click', '#fight', function() {
		$('#fight').button('disable')
		$('#fight').button('refresh')
		
		shuffling = true
		$('#choices img').trigger('startRumble')
		App.view.removeChoices()
		
		setTimeout(function() {
			App.req.nextRobot(opponent1, opponent2, function(data) {
				shuffling = false
				$('#choices img').trigger('stopRumble')
				App.view.updateStats(data.scores)
				App.view.showResult(data)
				$('#fight').button('enable')
				$('#fight').button('refresh')
			})		
		}, 1100)
	})
	
	$(document).on('click', '#first_choices.human_choices img', function() {
		if (shuffling) return
		
		shuffling = true
		var choice = $(this).attr('data-app')
		
		$('#choices img').trigger('startRumble')
		App.view.removeChoices()
		
		setTimeout(function() {
			App.req.nextHuman(opponent2, choice, function(data) {
				shuffling = false
				$('#choices img').trigger('stopRumble')
				App.view.updateStats(data.scores)
				App.view.showResult(data)
			})		
		}, 1100)
	})
	
	$(document).on('click', '#start_from_scratch', function() {
		App.req.nullify(opponent1, opponent2, function(data) {
			App.view.removeChoices()
			App.view.updateStats(data)
		})
	})
}


App.init = function() {
	App.page.initHumanList()
	App.page.initPcLists()
	App.page.initGame()
}