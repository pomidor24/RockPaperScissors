<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript">

            window.onerror = function(message, url, lineNumber) {
                console.log("Error: "+message+" in "+url+" at line "+lineNumber);
            }
        </script>
        <meta charset="utf-8" />
        <meta name="format-detection" content="telephone=no" />
        <meta name="viewport" content="user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width, height=device-height, target-densitydpi=device-dpi" />
        <link rel="stylesheet" href="vendors/jquery.mobile-1.3.1/jquery.mobile-1.3.1.min.css" />

		<style type="text/css">
			.score {
				font-size: 60px;
			}
			
			.score_draw {
				font-size: 40px;
			}
			
			#scores div, #choices div {
				text-align:center;
			}
			
			#choices img {
				height: 75px;
				width: 75px;
				padding: 15px;
				margin: 5px;
				-webkit-border-radius: 40px;
				-moz-border-radius: 40px;
				border-radius: 40px;	
				border: 0px;
				opacity: 0.8;
			}
			
			#first_choices.human_choices img {
				opacity: 1;
				border: 1px solid black;
			}

			#first_choices.human_choices a:hover img {
				border: 1px solid green;
			}
			
			#choices img.lost {
				background-color: #FF0000;
			}
			
			#choices img.won {
				background-color: green;
			}
			
			#choices img.draw {
				background-color: gray;
			}
			
		</style>

        <title>Rock Paper Scissors</title>


        <script src="vendors/jquery-2.0.0.min.js"></script>

        <script type="text/javascript">

            $(document).bind('mobileinit', function() {
            	$(document).ready(function() {
              		App.init()
              	})
            })
        </script>
        <script src="vendors/jquery.mobile-1.3.1/jquery.mobile-1.3.1.min.js"></script>
        <script src="vendors/jqm.page.params.js"></script>
        <script src="vendors/jquery.jrumble.1.3.min.js"></script>

        <script type="text/javascript">
            $(document).bind("pagebeforechange", function( event, data ) {
                $.mobile.pageData = (data && data.options && data.options.pageData)
                    ? data.options.pageData
                    : {};
            });
        </script>
        <script src="js/core.js"></script>

    </head>
    <body>

       <div id="menu" data-role="page" data-theme="a" data-content-theme="a">
            <div data-role="header">
                <h1>Rock Paper Scissors Battles</h1>
            </div>

            <div data-role="content">
            	<a href="#person_vs_cp" data-role="button" data-transition="flip"> Human vs Robot </a>
            	<a href="#cp_vs_cp" data-role="button" data-transition="flip"> Robot vs Robot </a>
            </div>
        </div>

       <div data-role="page" id="person_vs_cp">
            <div data-role="header">
                <a data-role="button" data-icon="back" data-rel="back">Back</a>
                <h2>Choose who you want to fight with</h2>
            </div>
            <div data-role="content">
                <ul id="human_opponents_list" data-role="listview">

                </ul>
            </div>
        </div>
	
       <div data-role="page" id="cp_vs_cp">
            <div data-role="header">
                <a data-role="button" data-icon="back" data-rel="back">Back</a>
                <h2>Select Fighters</h2>
            </div>
            <div data-role="content">
            	<div class="ui-grid-1">
            		<div class="ui-block-a">
            			<ul id="pc_opponents_list1" data-role="listview"></ul>
            		</div>
           			<div class="ui-block-b" >
           				<ul id="pc_opponents_list2" data-role="listview"></ul>
            		</div>
            	</div>
                
            </div>
        </div>
        
       <div data-role="page" id="game">
            <div data-role="header">
                <a data-role="button" data-icon="back" data-rel="back">Back</a>
                <h1> Prove them Wrong!</h1>
            </div>
            <div data-role="content">
                <div id="scores" class="ui-grid-b">
                    <div id="first_player" class="ui-block-a" >
                    </div>
                    <div class="ui-block-b">
                       <span style="font-size: 70px;"> VS </span>
                    </div>
                    <div id="second_player" class="ui-block-c">
                    </div>
                </div>
                <div id="choices" class="ui-grid-b" style="border-top: 1px solid gray">
                    <div id="first_choices" class="ui-block-a">
                    	<a href="#" ><img data-app="Rock" src="vendors/img/rock.png"/></a><br/>
                    	<a href="#" ><img data-app="Paper" src="vendors/img/paper.png"/></a><br/>
                    	<a href="#" ><img data-app="Scissors" src="vendors/img/scissors.png"/></a><br/>
                    </div>
                    <div class="ui-block-b" style="padding-top:70px">
                       
                       <div id="choices" class="ui-grid-b">
                       		<div class="ui-block-a">
                       			<span id="first_score" style="font-size: 60px;">0</span>
                       		</div>
                       		<div class="ui-block-b">
                       			<span style="font-size: 50px;">:</span><br/>
                       		    <span id="draw_score" style="font-size: 30px;padding-top:10px">0</span><br/>tied
                       		</div>
                       		<div class="ui-block-c">
                       			<span id="second_score" style="font-size: 60px;">0</span>
                       		</div>
                       </div>
                       <input id="fight" type="button" value="Fight" />
                       
                    </div>
                    <div id="second_choices" class="ui-block-c">
                    	<img data-app="Rock" src="vendors/img/rock.png"/><br/>
                    	<img data-app="Paper" src="vendors/img/paper.png"/><br/>
                    	<img data-app="Scissors" src="vendors/img/scissors.png"/><br/>
                    </div>
                </div>
                
            	<div data-role="header">
                	<h1></h1>
                	<a id="start_from_scratch" data-role="button" data-icon="delete" class="ui-btn-right">Start from scratch</a>
            	</div>
            </div>
        </div>
        
    </body>
</html>
