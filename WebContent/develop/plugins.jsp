<!DOCTYPE html>
<html lang="en-us">
	<head>
		<meta charset="utf-8">
		<!--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->

		<title> SmartAdmin </title>
		<meta name="description" content="">
		<meta name="author" content="">

		<!-- Use the correct meta names below for your web application
			 Ref: http://davidbcalhoun.com/2010/viewport-metatag 
			 
		<meta name="HandheldFriendly" content="True">
		<meta name="MobileOptimized" content="320">-->
		
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

		<!-- Basic Styles -->
		<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="css/font-awesome.min.css">

		<!-- SmartAdmin Styles : Please note (smartadmin-production.css) was created using LESS variables -->
		<link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-production.css">
		<link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-skins.css">

		<!-- SmartAdmin RTL Support is under construction
		<link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-rtl.css"> -->

		<!-- We recommend you use "your_style.css" to override SmartAdmin
		     specific styles this will also ensure you retrain your customization with each SmartAdmin update.
		<link rel="stylesheet" type="text/css" media="screen" href="css/your_style.css"> -->

		<!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->
		<link rel="stylesheet" type="text/css" media="screen" href="css/demo.css">

		<!-- FAVICONS -->
		<link rel="shortcut icon" href="img/favicon/favicon.ico" type="image/x-icon">
		<link rel="icon" href="img/favicon/favicon.ico" type="image/x-icon">

		<!-- GOOGLE FONT -->
		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,300,400,700">

	</head>
	<body class="">
		<!-- possible classes: minified, fixed-ribbon, fixed-header, fixed-width-->

		<!-- HEADER -->
		<header id="header">
			<div id="logo-group">

				<!-- PLACE YOUR LOGO HERE -->
				<span id="logo"> <img src="img/logo.png" alt="SmartAdmin"> </span>
				<!-- END LOGO PLACEHOLDER -->

				<!-- Note: The activity badge color changes when clicked and resets the number to 0
				Suggestion: You may want to set a flag when this happens to tick off all checked messages / notifications -->
				<span id="activity" class="activity-dropdown"> <i class="fa fa-user"></i> <b class="badge"> 21 </b> </span>

				<!-- AJAX-DROPDOWN : control this dropdown height, look and feel from the LESS variable file -->
				<div class="ajax-dropdown">

					<!-- the ID links are fetched via AJAX to the ajax container "ajax-notifications" -->
					<div class="btn-group btn-group-justified" data-toggle="buttons">
						<label class="btn btn-default">
							<input type="radio" name="activity" id="ajax/notify/mail.html">
							Msgs (14) </label>
						<label class="btn btn-default">
							<input type="radio" name="activity" id="ajax/notify/notifications.html">
							notify (3) </label>
						<label class="btn btn-default">
							<input type="radio" name="activity" id="ajax/notify/tasks.html">
							Tasks (4) </label>
					</div>

					<!-- notification content -->
					<div class="ajax-notifications custom-scroll">

						<div class="alert alert-transparent">
							<h4>Click a button to show messages here</h4>
							This blank page message helps protect your privacy, or you can show the first message here automatically.
						</div>

						<i class="fa fa-lock fa-4x fa-border"></i>

					</div>
					<!-- end notification content -->

					<!-- footer: refresh area -->
					<span> Last updated on: 12/12/2013 9:43AM
						<button type="button" data-loading-text="<i class='fa fa-refresh fa-spin'></i> Loading..." class="btn btn-xs btn-default pull-right">
							<i class="fa fa-refresh"></i>
						</button> </span>
					<!-- end footer -->

				</div>
				<!-- END AJAX-DROPDOWN -->
			</div>

			<!-- projects dropdown -->
			<div id="project-context">

				<span class="label">Projects:</span>
				<span id="project-selector" class="popover-trigger-element dropdown-toggle" data-toggle="dropdown">Recent projects <i class="fa fa-angle-down"></i></span>

				<!-- Suggestion: populate this list with fetch and push technique -->
				<ul class="dropdown-menu">
					<li>
						<a href="javascript:void(0);">Online e-merchant management system - attaching integration with the iOS</a>
					</li>
					<li>
						<a href="javascript:void(0);">Notes on pipeline upgradee</a>
					</li>
					<li>
						<a href="javascript:void(0);">Assesment Report for merchant account</a>
					</li>
					<li class="divider"></li>
					<li>
						<a href="javascript:void(0);"><i class="fa fa-power-off"></i> Clear</a>
					</li>
				</ul>
				<!-- end dropdown-menu-->

			</div>
			<!-- end projects dropdown -->

			<!-- pulled right: nav area -->
			<div class="pull-right">

				<!-- collapse menu button -->
				<div id="hide-menu" class="btn-header pull-right">
					<span> <a href="javascript:void(0);" title="Collapse Menu"><i class="fa fa-reorder"></i></a> </span>
				</div>
				<!-- end collapse menu -->

				<!-- logout button -->
				<div id="logout" class="btn-header transparent pull-right">
					<span> <a href="login.html" title="Sign Out"><i class="fa fa-sign-out"></i></a> </span>
				</div>
				<!-- end logout button -->

				<!-- search mobile button (this is hidden till mobile view port) -->
				<div id="search-mobile" class="btn-header transparent pull-right">
					<span> <a href="javascript:void(0)" title="Search"><i class="fa fa-search"></i></a> </span>
				</div>
				<!-- end search mobile button -->

				<!-- input: search field -->
				<form action="#search.html" class="header-search pull-right">
					<input type="text" placeholder="Find reports and more" id="search-fld">
					<button type="submit">
						<i class="fa fa-search"></i>
					</button>
					<a href="javascript:void(0);" id="cancel-search-js" title="Cancel Search"><i class="fa fa-times"></i></a>
				</form>
				<!-- end input: search field -->

				<!-- multiple lang dropdown : find all flags in the image folder -->
				<ul class="header-dropdown-list hidden-xs">
					<li>
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"> <img alt="" src="img/flags/us.png"> <span> US </span> <i class="fa fa-angle-down"></i> </a>
						<ul class="dropdown-menu pull-right">
							<li class="active">
								<a href="javascript:void(0);"><img alt="" src="img/flags/us.png"> US</a>
							</li>
							<li>
								<a href="javascript:void(0);"><img alt="" src="img/flags/es.png"> Spanish</a>
							</li>
							<li>
								<a href="javascript:void(0);"><img alt="" src="img/flags/de.png"> German</a>
							</li>
						</ul>
					</li>
				</ul>
				<!-- end multiple lang -->

			</div>
			<!-- end pulled right: nav area -->

		</header>
		<!-- END HEADER -->

		<!-- Left panel : Navigation area -->
		<!-- Note: This width of the aside area can be adjusted through LESS variables -->
		<aside id="left-panel">

			<!-- User info -->
			<div class="login-info">
				<span> <!-- User image size is adjusted inside CSS, it should stay as it --> 
					
					<a href="javascript:void(0);" id="show-shortcut">
						<img src="img/avatars/sunny.png" alt="me" class="online" /> 
						<span>
							john.doe 
						</span>
						<i class="fa fa-angle-down"></i>
					</a> 
					
				</span>
			</div>
			<!-- end user info -->

			<!-- NAVIGATION : This navigation is also responsive

			To make this navigation dynamic please make sure to link the node
			(the reference to the nav > ul) after page load. Or the navigation
			will not initialize.
			-->
			<nav>
				<!-- NOTE: Notice the gaps after each icon usage <i></i>..
				Please note that these links work a bit different than
				traditional hre="" links. See documentation for details.
				-->

				<ul>
					<li>
						<a href="index.html" title="Dashboard"><i class="fa fa-lg fa-fw fa-home"></i> <span class="menu-item-parent">Dashboard</span></a>
					</li>
					<li>
						<a href="inbox.html"><i class="fa fa-lg fa-fw fa-inbox"></i> <span class="menu-item-parent">Inbox</span><span class="badge pull-right inbox-badge">14</span></a>
					</li>
					<li>
						<a href="#"><i class="fa fa-lg fa-fw fa-bar-chart-o"></i> <span class="menu-item-parent">Graphs</span></a>
						<ul>
							<li>
								<a href="flot.html">Flot Chart</a>
							</li>
							<li>
								<a href="morris.html">Morris Charts</a>
							</li>
							<li>
								<a href="inline-charts.html">Inline Charts</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="#"><i class="fa fa-lg fa-fw fa-table"></i> <span class="menu-item-parent">Tables</span></a>
						<ul>
							<li>
								<a href="table.html">Normal Tables</a>
							</li>
							<li>
								<a href="datatables.html">Data Tables</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="#"><i class="fa fa-lg fa-fw fa-pencil-square-o"></i> <span class="menu-item-parent">Forms</span></a>
						<ul>
							<li>
								<a href="form-elements.html">Smart Form Elements</a>
							</li>
							<li>
								<a href="form-templates.html">Smart Form Layouts</a>
							</li>
							<li>
								<a href="validation.html">Smart Form Validation</a>
							</li>
							<li>
								<a href="bootstrap-forms.html">Bootstrap Form Elements</a>
							</li>
							<li class="active">
								<a href="plugins.html">Form Plugins</a>
							</li>
							<li>
								<a href="wizard.html">Wizards</a>
							</li>
							<li>
								<a href="other-editors.html">Bootstrap Editors</a>
							</li>
							<li>
								<a href="dropzone.html">Dropzone <span class="badge pull-right inbox-badge bg-color-yellow">new</span></a>
							</li>
						</ul>
					</li>
					<li>
						<a href="#"><i class="fa fa-lg fa-fw fa-desktop"></i> <span class="menu-item-parent">UI Elements</span></a>
						<ul>
							<li>
								<a href="general-elements.html">General Elements</a>
							</li>
							<li>
								<a href="buttons.html">Buttons</a>
							</li>
							<li>
								<a href="#">Icons</a>
								<ul>
									<li>
										<a href="fa.html"><i class="fa fa-plane"></i> Font Awesome</a>
									</li>	
									<li>
										<a href="glyph.html"><i class="glyphicon glyphicon-plane"></i> Glyph Icons </a>
									</li>	
								</ul>
							</li>
							<li>
								<a href="grid.html">Grid</a>
							</li>
							<li>
								<a href="treeview.html">Tree View</a>
							</li>
							<li>
								<a href="nestable-list.html">Nestable Lists</a>
							</li>
							<li>
								<a href="jqui.html">JQuery UI</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="#"><i class="fa fa-lg fa-fw fa-folder-open"></i> <span class="menu-item-parent">6 Level Navigation</span></a>
						<ul>
							<li>
								<a href="#"><i class="fa fa-fw fa-folder-open"></i> 2nd Level</a>
								<ul>
									<li>
										<a href="#"><i class="fa fa-fw fa-folder-open"></i> 3ed Level </a>
										<ul>
											<li>
												<a href="#"><i class="fa fa-fw fa-file-text"></i> File</a>
											</li>
											<li>
												<a href="#"><i class="fa fa-fw fa-folder-open"></i> 4th Level</a>
												<ul>
													<li>
														<a href="#"><i class="fa fa-fw fa-file-text"></i> File</a>
													</li>
													<li>
														<a href="#"><i class="fa fa-fw fa-folder-open"></i> 5th Level</a>
														<ul>
															<li>
																<a href="#"><i class="fa fa-fw fa-file-text"></i> File</a>
															</li>
															<li>
																<a href="#"><i class="fa fa-fw fa-file-text"></i> File</a>
															</li>
														</ul>
													</li>
												</ul>
											</li>
										</ul>
									</li>
								</ul>
							</li>
							<li>
								<a href="#"><i class="fa fa-fw fa-folder-open"></i> Folder</a>

								<ul>
									<li>
										<a href="#"><i class="fa fa-fw fa-folder-open"></i> 3ed Level </a>
										<ul>
											<li>
												<a href="#"><i class="fa fa-fw fa-file-text"></i> File</a>
											</li>
											<li>
												<a href="#"><i class="fa fa-fw fa-file-text"></i> File</a>
											</li>
										</ul>
									</li>
								</ul>

							</li>
						</ul>
					</li>
					<li>
						<a href="calendar.html"><i class="fa fa-lg fa-fw fa-calendar"><em>3</em></i> <span class="menu-item-parent">Calendar</span></a>
					</li>
					<li>
						<a href="widgets.html"><i class="fa fa-lg fa-fw fa-list-alt"></i> <span class="menu-item-parent">Widgets</span></a>
					</li>
					<li>
						<a href="gallery.html"><i class="fa fa-lg fa-fw fa-picture-o"></i> <span class="menu-item-parent">Gallery</span></a>
					</li>
					<li>
						<a href="gmap-xml.html"><i class="fa fa-lg fa-fw fa-map-marker"></i> <span class="menu-item-parent">Google Map Skins</span><span class="badge bg-color-greenLight pull-right inbox-badge">9</span></a>
					</li>
					<li>
						<a href="#"><i class="fa fa-lg fa-fw fa-windows"></i> <span class="menu-item-parent">Miscellaneous</span></a>
						<ul>
							<li>
								<a href="typography.html">Typography</a>
							</li>
							<li>
								<a href="pricing-table.html">Pricing Tables</a>
							</li>
							<li>
								<a href="invoice.html">Invoice</a>
							</li>
							<li>
								<a href="login.html" target="_top">Login</a>
							</li>
							<li>
								<a href="register.html" target="_top">Register</a>
							</li>
							<li>
								<a href="lock.html" target="_top">Locked Screen</a>
							</li>
							<li>
								<a href="error404.html">Error 404</a>
							</li>
							<li>
								<a href="error500.html">Error 500</a>
							</li>
							<li>
								<a href="blank_.html">Blank Page</a>
							</li>
							<li>
								<a href="email-template.html">Email Template</a>
							</li>
							<li>
								<a href="search.html">Search Page</a>
							</li>
							<li>
								<a href="ckeditor.html">CK Editor</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="#"><i class="fa fa-lg fa-fw fa-file"></i> <span class="menu-item-parent">Other Pages</span></a>
						<ul>
							<li>
								<a href="forum.html">Forum Layout</a>
							</li>
							<li>
								<a href="profile.html">Profile</a>
							</li>
							<li>
								<a href="timeline.html">Timeline</a>
							</li>
						</ul>
					</li>
				</ul>
			</nav>
			<span class="minifyme"> <i class="fa fa-arrow-circle-left hit"></i> </span>

		</aside>
		<!-- END NAVIGATION -->

		<!-- MAIN PANEL -->
		<div id="main" role="main">

			<!-- RIBBON -->
			<div id="ribbon">

				<span class="ribbon-button-alignment"> <span id="refresh" class="btn btn-ribbon" data-title="refresh"  rel="tooltip" data-placement="bottom" data-original-title="<i class='text-warning fa fa-warning'></i> Warning! This will reset all your widget settings." data-html="true"><i class="fa fa-refresh"></i></span> </span>

				<!-- breadcrumb -->
				<ol class="breadcrumb">
					<li>
						Forms
					</li>
					<li>
						Form Plugins
					</li>
				</ol>
				<!-- end breadcrumb -->

				<!-- You can also add more buttons to the
				ribbon for further usability

				Example below:

				<span class="ribbon-button-alignment pull-right">
				<span id="search" class="btn btn-ribbon hidden-xs" data-title="search"><i class="fa-grid"></i> Change Grid</span>
				<span id="add" class="btn btn-ribbon hidden-xs" data-title="add"><i class="fa-plus"></i> Add</span>
				<span id="search" class="btn btn-ribbon" data-title="search"><i class="fa-search"></i> <span class="hidden-mobile">Search</span></span>
				</span> -->

			</div>
			<!-- END RIBBON -->

			<!-- MAIN CONTENT -->
			<div id="content">

				<div class="row">
					<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
						<h1 class="page-title txt-color-blueDark">
							<i class="fa fa-pencil-square-o fa-fw "></i> 
								Forms
							<span>> 
								Form Plugins
							</span>
						</h1>
					</div>
					<div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
						<ul id="sparks" class="">
							<li class="sparks-info">
								<h5> My Income <span class="txt-color-blue">$47,171</span></h5>
								<div class="sparkline txt-color-blue hidden-mobile hidden-md hidden-sm">
									1300, 1877, 2500, 2577, 2000, 2100, 3000, 2700, 3631, 2471, 2700, 3631, 2471
								</div>
							</li>
							<li class="sparks-info">
								<h5> Site Traffic <span class="txt-color-purple"><i class="fa fa-arrow-circle-up" data-rel="bootstrap-tooltip" title="Increased"></i>&nbsp;45%</span></h5>
								<div class="sparkline txt-color-purple hidden-mobile hidden-md hidden-sm">
									110,150,300,130,400,240,220,310,220,300, 270, 210
								</div>
							</li>
							<li class="sparks-info">
								<h5> Site Orders <span class="txt-color-greenDark"><i class="fa fa-shopping-cart"></i>&nbsp;2447</span></h5>
								<div class="sparkline txt-color-greenDark hidden-mobile hidden-md hidden-sm">
									110,150,300,130,400,240,220,310,220,300, 270, 210
								</div>
							</li>
						</ul>
					</div>
				</div>
				
				<!-- widget grid -->
				<section id="widget-grid" class="">
				
					<!-- row -->
					<div class="row">
				
						<!-- NEW COL START -->
						<article class="col-sm-12">
				
							<!-- Widget ID (each widget will need unique ID)-->
							<div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-custombutton="false">
								<!-- widget options:
								usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
				
								data-widget-colorbutton="false"
								data-widget-editbutton="false"
								data-widget-togglebutton="false"
								data-widget-deletebutton="false"
								data-widget-fullscreenbutton="false"
								data-widget-custombutton="false"
								data-widget-collapsed="true"
								data-widget-sortable="false"
				
								-->
								<header>
									<span class="widget-icon"> <i class="fa fa-edit"></i> </span>
									<h2>x-ediable </h2>
				
								</header>
				
								<!-- widget div-->
								<div>
				
									<!-- widget edit box -->
									<div class="jarviswidget-editbox">
										<!-- This area used as dropdown edit box -->
				
									</div>
									<!-- end widget edit box -->
				
									<!-- widget content -->
									<div class="widget-body">
										<div class="widget-body-toolbar">
											
											<div class="row">
												
												<div class="col-sm-6">
													<button id="enable" class="btn btn btn-default">
														enable / disable
													</button>
												</div>
												<div class="col-sm-6 text-right">
													
													<div class="onoffswitch-container">
														<span class="onoffswitch-title">Auto Open Next</span> 
														<span class="onoffswitch">
															<input type="checkbox" class="onoffswitch-checkbox" id="autoopen">
															<label class="onoffswitch-label" for="autoopen"> 
																<span class="onoffswitch-inner" data-swchon-text="ON" data-swchoff-text="OFF"></span> 
																<span class="onoffswitch-switch"></span>
															</label> 
														</span> 
														
														
													</div>
													
													<div class="onoffswitch-container">
														<span class="onoffswitch-title">Open Inline</span> 
														<span class="onoffswitch">
															<input type="checkbox" class="onoffswitch-checkbox" id="inline">
															<label class="onoffswitch-label" for="inline"> 
																<span class="onoffswitch-inner" data-swchon-text="ON" data-swchoff-text="OFF"></span> 
																<span class="onoffswitch-switch"></span>
															</label> 
														</span>
													</div>	
													
												</div>
												
											</div>
												
				
										</div>
				
										<table id="user" class="table table-bordered table-striped" style="clear: both">
											<tbody>
												<tr>
													<td style="width:35%;">Simple text field</td>
													<td style="width:65%"><a href="form-x-editable.html#" id="username" data-type="text" data-pk="1" data-original-title="Enter username">superuser</a></td>
												</tr>
												<tr>
													<td>Empty text field, required</td>
													<td><a href="form-x-editable.html#" id="firstname" data-type="text" data-pk="1" data-placement="right" data-placeholder="Required" data-original-title="Enter your firstname"></a></td>
												</tr>
												<tr>
													<td>Select, local array, custom display</td>
													<td><a href="form-x-editable.html#" id="sex" data-type="select" data-pk="1" data-value="" data-original-title="Select sex"></a></td>
												</tr>
												<tr>
													<td>Select, remote array, no buttons</td>
													<td><a href="form-x-editable.html#" id="group" data-type="select" data-pk="1" data-value="5" data-source="/groups" data-original-title="Select group">Admin</a></td>
												</tr>
												<tr>
													<td>Select, error while loading</td>
													<td><a href="form-x-editable.html#" id="status" data-type="select" data-pk="1" data-value="0" data-source="/status" data-original-title="Select status">Active</a></td>
												</tr>
				
												<tr>
													<td>Datepicker</td>
													<td><a href="#" id="vacation" data-type="date" data-viewformat="dd.mm.yyyy" data-pk="1" data-placement="right" data-original-title="When you want vacation to start?">25.02.2013</a></td>
												</tr>
												<tr>
													<td>Combodate (date)</td>
													<td><a href="form-x-editable.html#" id="dob" data-type="combodate" data-value="1984-05-15" data-format="YYYY-MM-DD" data-viewformat="DD/MM/YYYY" data-template="D / MMM / YYYY" data-pk="1" data-original-title="Select Date of birth"></a></td>
												</tr>
												<tr>
													<td>Combodate (datetime)</td>
													<td><a href="form-x-editable.html#" id="event" data-type="combodate" data-template="D MMM YYYY  HH:mm" data-format="YYYY-MM-DD HH:mm" data-viewformat="MMM D, YYYY, HH:mm" data-pk="1" data-original-title="Setup event date and time"></a></td>
												</tr>
				
												<tr>
													<td>Textarea, buttons below. Submit by <i>ctrl+enter</i></td>
													<td><a href="form-x-editable.html#" id="comments" data-type="textarea" data-pk="1" data-placeholder="Your comments here..." data-original-title="Enter comments">awesome user!</a></td>
												</tr>
				
												<tr>
													<td>Twitter typeahead.js</td>
													<td><a href="form-x-editable.html#" id="state2" data-type="typeaheadjs" data-pk="1" data-placement="right" data-original-title="Start typing State.."></a></td>
												</tr>
				
												<tr>
													<td>Checklist</td>
													<td><a href="form-x-editable.html#" id="fruits" data-type="checklist" data-value="2,3" data-original-title="Select fruits"></a></td>
												</tr>
				
												<tr>
													<td>Select2 (tags mode)</td>
													<td><a href="form-x-editable.html#" id="tags" data-type="select2" data-pk="1" data-original-title="Enter tags">html, javascript</a></td>
												</tr>
				
												<tr>
													<td>Select2 (dropdown mode)</td>
													<td><a href="form-x-editable.html#" id="country" data-type="select2" data-pk="1" data-select-search="true" data-value="BS" data-original-title="Select country"></a></td>
												</tr>
				
												<tr>
													<td>Custom input, several fields</td>
													<td><a href="form-x-editable.html#" id="address" data-type="address" data-pk="1" data-original-title="Please, fill address"></a></td>
												</tr>
				
											</tbody>
										</table>
				
									</div>
									<!-- end widget content -->
				
								</div>
								<!-- end widget div -->
				
							</div>
							<!-- end widget -->
				
							<!-- Widget ID (each widget will need unique ID)-->
							<div class="jarviswidget jarviswidget-color-darken" id="wid-id-1" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-custombutton="false">
								<!-- widget options:
								usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
				
								data-widget-colorbutton="false"
								data-widget-editbutton="false"
								data-widget-togglebutton="false"
								data-widget-deletebutton="false"
								data-widget-fullscreenbutton="false"
								data-widget-custombutton="false"
								data-widget-collapsed="true"
								data-widget-sortable="false"
				
								-->
								<header>
									<span class="widget-icon"> <i class="fa fa-edit"></i> </span>
									<h2>Sliders </h2>
				
								</header>
				
								<!-- widget div-->
								<div>
				
									<!-- widget edit box -->
									<div class="jarviswidget-editbox">
										<!-- This area used as dropdown edit box -->
				
									</div>
									<!-- end widget edit box -->
				
									<!-- widget content -->
									<div class="widget-body">
				
										<form>
				
											<fieldset>
												<legend>
													Smart Scale Slider
												</legend>
												<div class="row">
													<div class="col-sm-6">
				
														<div class="form-group">
															<input id="range-slider-1" type="text" name="range_1" value="">
														</div>
													</div>
													<div class="col-sm-6">
				
														<div class="form-group">
															<input id="range-slider-2" type="text" name="range_2" value="1000;100000" data-type="double" data-step="500" data-postfix=" €" data-from="30000" data-to="90000" data-hasgrid="true">
														</div>
													</div>
												</div>
				
												<div class="row">
													<div class="col-sm-6">
				
														<div class="form-group">
															<input id="range-slider-3" type="text" name="range_2a" value="">
														</div>
													</div>
													<div class="col-sm-6">
				
														<div class="form-group">
															<input id="range-slider-4" type="text" name="range_4" value="">
														</div>
													</div>
												</div>
				
												<div class="row">
													<div class="col-sm-12">
				
														<div class="form-group">
															<input id="range-slider-5" type="text" name="range_5a" value="">
														</div>
													</div>
												</div>
				
											</fieldset>
				
											<fieldset>
												<legend>
													noScale Slider
												</legend>
												<div class="row">
													<div class="col-sm-6">
				
														<div class="form-group">
															<label>Default</label>
															<div id="nouislider-1" class="noUiSlider"></div>
														</div>
													</div>
													<div class="col-sm-6">
				
														<div class="form-group">
															<label>Range slider (<span class="nouislider-value">20 - 60</span>)</label>
															<div id="nouislider-3" class="noUiSlider"></div>
														</div>
				
													</div>
												</div>
				
				
												<div class="row">
													<div class="col-sm-6">
				
														<div class="form-group">
															<label>Default Slider (disabled)</label>
															<div id="nouislider-4" class="noUiSlider"></div>
														</div>
													</div>
													<div class="col-sm-6">
				
														<div class="form-group">
															<label>Skips a beat</label>
															<div id="nouislider-2" class="noUiSlider"></div>
														</div>
				
													</div>
												</div>
											</fieldset>
											
											<fieldset class="margin-top-10">
												
												<legend>
													JQuery UI Slider
												</legend>
												<div class="row">
													<div class="col-sm-6">
														<label><code>.slider .slider-danger</code></label>
														<input type="text" class="slider slider-danger" id="sal" value="" 
														data-slider-min="10" 
														data-slider-max="1000" 
														data-slider-step="1" 
														data-slider-value="[50,450]"
														data-slider-handle="round">
													</div>
													<div class="col-sm-6">
														<label><code>.slider .slider-success</code></label>
														<input type="text" class="slider slider-success" id="sa2" value="" 
														data-slider-min="10" 
														data-slider-max="1000" 
														data-slider-step="1" 
														data-slider-value="[150,760]" 
														data-slider-handle="triangle">
													</div>
												</div>	
												<div class="row">
													<div class="col-sm-6">
														<label><code>.slider .slider-warning</code></label>
														<input type="text" class="slider slider-warning" id="sa3" value="" 
														data-slider-min="1" 
														data-slider-max="300"  
														data-slider-value="150" 
														data-slider-selection = "before"
														data-slider-handle="squar">
													</div>
													<div class="col-sm-6">
														<label><code>.slider .slider-info</code></label>
														<input type="text" class="slider slider-info" id="sa4" value="" 
														data-slider-min="1" 
														data-slider-max="300"  
														data-slider-value="150" 
														data-slider-selection = "after"
														data-slider-handle="round">
													</div>
												</div>	
												
												<div class="row">
													<div class="col-sm-12">
														<label><code>.slider .slider-primary</code></label>
														<input type="text" class="slider slider-primary" id="sa5" value="" 
														data-slider-min="1" 
														data-slider-max="300"  
														data-slider-value="150" 
														data-slider-selection = "before"
														data-slider-handle="round">
													</div>
												</div>	
												
												<div class="row">
													
													<div class="col-sm-6">
											
														<pre><strong class="margin-top-10 margin-bottom-10 font-lg">Usage</strong><br>
				<code><strong>&lt;input data-slider-min="10" ..  /&gt;</strong></code>
				data-slider-orientation="vertical" <span class="text-muted"> // vertical or horizontal</span>
				data-slider-step="1"               <span class="text-muted"> // increment step</span>
				data-slider-min="10"               <span class="text-muted"> // slider min value</span>
				data-slider-max="500"              <span class="text-muted"> // slider max value</span>
				data-slider-value="315"            <span class="text-muted"> // handler position on slider</span>
				data-slider-selection = "after"    <span class="text-muted"> // handler position on slider</span>
				data-slider-handle="round"         <span class="text-muted"> // round or square</span> 
				data-slider-tooltip = "show"       <span class="text-muted"> // show or hide</span> 
														</pre>
													</div>
													
													<div class="col-sm-6">
														
														<div class="well">
								
												          <table>
												            <tbody>
												              <tr>
												                <td>
												                  <input type="text" class="slider slider-danger" value="" 
												                  data-slider-min="-20" 
												                  data-slider-max="20" 
												                  data-slider-step="1" 
												                  data-slider-value="-13" 
												                  data-slider-orientation="vertical" 
												                  data-slider-selection="after" 
												                  data-slider-handle="square" 
												                  data-slider-tooltip="hide">
												                </td>
												                <td>
												                  <input type="text" class="slider" value="" 
												                  data-slider-min="-20" 
												                  data-slider-max="20" 
												                  data-slider-step="1" 
												                  data-slider-value="-11" 
												                  data-slider-orientation="vertical" 
												                  data-slider-selection="after" 
												                  data-slider-handle="triangle" 
												                  data-slider-tooltip="hide">
												                </td>
												                <td>
												                  <input type="text" class="slider" value="" 
												                  data-slider-min="-20" 
												                  data-slider-max="20" 
												                  data-slider-step="1" 
												                  data-slider-value="-6" 
												                  data-slider-orientation="vertical" 
												                  data-slider-selection="after" 
												                  data-slider-tooltip="hide">
												                </td>
												                <td>
												                  <input type="text" class="slider" value="" 
												                  data-slider-min="-20" 
												                  data-slider-max="20" 
												                  data-slider-step="1" 
												                  data-slider-value="-4" 
												                  data-slider-orientation="vertical" 
												                  data-slider-selection="after" 
												                  data-slider-tooltip="hide">
												                </td>
												                <td>
												                  <input type="text" class="slider" value="" 
												                  data-slider-min="-20" 
												                  data-slider-max="20" 
												                  data-slider-step="1" 
												                  data-slider-value="-6" 
												                  data-slider-orientation="vertical" 
												                  data-slider-selection="after" 
												                  data-slider-tooltip="hide">
												                </td>
												                <td>
												                  <input type="text" class="slider slider-warning" value="" 
												                  data-slider-min="-20" 
												                  data-slider-max="20" 
												                  data-slider-step="1" 
												                  data-slider-value="[-11, 19]" 
												                  data-slider-orientation="vertical" 
												                  data-slider-selection="after" 
												                  data-slider-handle="triangle" 
												                  data-slider-tooltip="show">
												                </td>
												                <td>
												                  <input type="text" class="slider slider-success" value="" 
												                  data-slider-min="-20" 
												                  data-slider-max="20" 
												                  data-slider-step="1" 
												                  data-slider-value="-17" 
												                  data-slider-orientation="vertical" 
												                  data-slider-selection="after" 
												                  data-slider-tooltip="show">
												                </td>
												              </tr>
												            </tbody>
												          </table>
												          
					      								</div>
														
													</div>
													
												</div>	
											</fieldset>	
				
											
										</form>
				
									</div>
									<!-- end widget content -->
				
								</div>
								<!-- end widget div -->
				
							</div>
							<!-- end widget -->
				
						</article>
						<!-- END COL -->
				
					</div>
				
					<!-- end row -->
				
					<!-- START ROW -->
				
					<div class="row">
				
						<!-- NEW COL START -->
						<article class="col-sm-12 col-md-12 col-lg-6">
				
							<!-- Widget ID (each widget will need unique ID)-->
							<div class="jarviswidget" id="wid-id-2" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-custombutton="false">
								<!-- widget options:
								usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
				
								data-widget-colorbutton="false"
								data-widget-editbutton="false"
								data-widget-togglebutton="false"
								data-widget-deletebutton="false"
								data-widget-fullscreenbutton="false"
								data-widget-custombutton="false"
								data-widget-collapsed="true"
								data-widget-sortable="false"
				
								-->
								<header>
									<span class="widget-icon"> <i class="fa fa-edit"></i> </span>
									<h2>Plugins & Enhancers </h2>
				
								</header>
				
								<!-- widget div-->
								<div>
				
									<!-- widget edit box -->
									<div class="jarviswidget-editbox">
										<!-- This area used as dropdown edit box -->
				
									</div>
									<!-- end widget edit box -->
				
									<!-- widget content -->
									<div class="widget-body">
				
										<form class="">
				
											<fieldset>
												<legend>
													Select 2
												</legend>
				
												<div class="form-group">
													<label>Select2 Plugin (select)</label>
													<select style="width:100%" class="select2">
														<optgroup label="Alaskan/Hawaiian Time Zone">
															<option value="AK">Alaska</option>
															<option value="HI">Hawaii</option>
														</optgroup>
														<optgroup label="Pacific Time Zone">
															<option value="CA">California</option>
															<option value="NV">Nevada</option>
															<option value="OR">Oregon</option>
															<option value="WA">Washington</option>
														</optgroup>
														<optgroup label="Mountain Time Zone">
															<option value="AZ">Arizona</option>
															<option value="CO">Colorado</option>
															<option value="ID">Idaho</option>
															<option value="MT">Montana</option><option value="NE">Nebraska</option>
															<option value="NM">New Mexico</option>
															<option value="ND">North Dakota</option>
															<option value="UT">Utah</option>
															<option value="WY">Wyoming</option>
														</optgroup>
														<optgroup label="Central Time Zone">
															<option value="AL">Alabama</option>
															<option value="AR">Arkansas</option>
															<option value="IL">Illinois</option>
															<option value="IA">Iowa</option>
															<option value="KS">Kansas</option>
															<option value="KY">Kentucky</option>
															<option value="LA">Louisiana</option>
															<option value="MN">Minnesota</option>
															<option value="MS">Mississippi</option>
															<option value="MO">Missouri</option>
															<option value="OK">Oklahoma</option>
															<option value="SD">South Dakota</option>
															<option value="TX">Texas</option>
															<option value="TN">Tennessee</option>
															<option value="WI">Wisconsin</option>
														</optgroup>
														<optgroup label="Eastern Time Zone">
															<option value="CT">Connecticut</option>
															<option value="DE">Delaware</option>
															<option value="FL">Florida</option>
															<option value="GA">Georgia</option>
															<option value="IN">Indiana</option>
															<option value="ME">Maine</option>
															<option value="MD">Maryland</option>
															<option value="MA">Massachusetts</option>
															<option value="MI">Michigan</option>
															<option value="NH">New Hampshire</option><option value="NJ">New Jersey</option>
															<option value="NY">New York</option>
															<option value="NC">North Carolina</option>
															<option value="OH">Ohio</option>
															<option value="PA">Pennsylvania</option><option value="RI">Rhode Island</option><option value="SC">South Carolina</option>
															<option value="VT">Vermont</option><option value="VA">Virginia</option>
															<option value="WV">West Virginia</option>
														</optgroup>
													</select>
				
													<div class="note">
														<strong>Usage:</strong> &lt;select style=&quot;width:100%&quot; class=&quot;select2&quot; &quot;&gt;...&lt;/select&gt;
													</div>
												</div>
				
												<div class="form-group">
													<label>Select2 Plugin (multi-select)</label>
													<select multiple style="width:100%" class="select2">
														<optgroup label="Alaskan/Hawaiian Time Zone">
															<option value="AK">Alaska</option>
															<option value="HI">Hawaii</option>
														</optgroup>
														<optgroup label="Pacific Time Zone">
															<option value="CA">California</option>
															<option value="NV" selected="selected">Nevada</option>
															<option value="OR">Oregon</option>
															<option value="WA">Washington</option>
														</optgroup>
														<optgroup label="Mountain Time Zone">
															<option value="AZ">Arizona</option>
															<option value="CO">Colorado</option>
															<option value="ID">Idaho</option>
															<option value="MT" selected="selected">Montana</option><option value="NE">Nebraska</option>
															<option value="NM">New Mexico</option>
															<option value="ND">North Dakota</option>
															<option value="UT">Utah</option>
															<option value="WY">Wyoming</option>
														</optgroup>
														<optgroup label="Central Time Zone">
															<option value="AL">Alabama</option>
															<option value="AR">Arkansas</option>
															<option value="IL">Illinois</option>
															<option value="IA">Iowa</option>
															<option value="KS">Kansas</option>
															<option value="KY">Kentucky</option>
															<option value="LA">Louisiana</option>
															<option value="MN">Minnesota</option>
															<option value="MS">Mississippi</option>
															<option value="MO">Missouri</option>
															<option value="OK">Oklahoma</option>
															<option value="SD">South Dakota</option>
															<option value="TX">Texas</option>
															<option value="TN">Tennessee</option>
															<option value="WI">Wisconsin</option>
														</optgroup>
														<optgroup label="Eastern Time Zone">
															<option value="CT">Connecticut</option>
															<option value="DE">Delaware</option>
															<option value="FL">Florida</option>
															<option value="GA">Georgia</option>
															<option value="IN">Indiana</option>
															<option value="ME">Maine</option>
															<option value="MD">Maryland</option>
															<option value="MA">Massachusetts</option>
															<option value="MI" selected="selected">Michigan</option>
															<option value="NH">New Hampshire</option>
															<option value="NJ">New Jersey</option>
															<option value="NY">New York</option>
															<option value="NC">North Carolina</option>
															<option value="OH">Ohio</option>
															<option value="PA">Pennsylvania</option>
															<option value="RI">Rhode Island</option>
															<option value="SC">South Carolina</option>
															<option value="VT">Vermont</option>
															<option value="VA">Virginia</option>
															<option value="WV">West Virginia</option>
														</optgroup>
													</select>
				
													<div class="note">
														<strong>Usage:</strong> &lt;select multiple style=&quot;width:100%&quot; class=&quot;select2&quot; &gt;...&lt;/select&gt;
													</div>
												</div>
				
											</fieldset>
				
											<fieldset>
												<legend>
													Date Picker (Jquery UI)
												</legend>
				
												<div class="row">
													<div class="col-sm-12">
														<div class="form-group">
															<label>Select a date (single):</label>
															<div class="input-group">
																<input type="text" name="mydate" placeholder="Select a date" class="form-control datepicker" data-dateformat="dd/mm/yy">
																<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
															</div>
														</div>
													</div>
				
													<div class="col-sm-12">
														<label>Select a date (range):</label>
													</div>
													<div class="col-sm-6">
				
														<div class="form-group">
															<div class="input-group">
																<input class="form-control" id="from" type="text" placeholder="From">
																<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
															</div>
														</div>
				
													</div>
													<div class="col-sm-6">
				
														<div class="form-group">
															<div class="input-group">
																<input class="form-control" id="to" type="text" placeholder="Select a date">
																<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
															</div>
														</div>
				
													</div>
				
												</div>
											</fieldset>
				
											<fieldset>
												<legend>
													Bootstrap Timepicker
												</legend>
				
												<div class="row">
				
													<div class="col-sm-12">
														<div class="row">
				
															<div class="col-sm-12">
																<div class="form-group">
																	<label>Timepicker (default):</label>
																	<div class="input-group">
																		<input class="form-control" id="timepicker" type="text" placeholder="Select time">
																		<span class="input-group-addon"><i class="fa fa-clock-o"></i></span>
																	</div>
																</div>
															</div>
				
														</div>
													</div>
				
												</div>
				
											</fieldset>
				
											<fieldset>
												<legend>
													Spinners
												</legend>
				
												<div class="row">
				
													<div class="col-sm-6 col-md-4 col-lg-4">
				
														<div class="form-group">
															<label>Default</label>
															<input class="form-control spinner-left"  id="spinner" name="spinner" value="1" type="text">
														</div>
				
													</div>
													<div class="col-sm-6 col-md-4 col-lg-4">
														<div class="form-group">
															<label>Decimal spinner</label>
															<input class="form-control"  id="spinner-decimal" name="spinner-decimal" value="7.99">
														</div>
													</div>
													<div class="col-sm-12 col-md-4 col-lg-4">
														<div class="form-group">
															<label>Increment spinner</label>
															<input class="form-control spinner-both"  id="spinner-currency" name="spinner-currency" value="5">
														</div>
													</div>
												</div>
				
											</fieldset>
				
											<fieldset>
												<legend>
													Color Pickers
												</legend>
				
												<div class="row">
				
													<div class="col-sm-6">
				
														<div class="form-group">
															<label>Color Picker (HEX)</label>
															<input class="form-control" id="colorpicker-1" type="text" value="#8fff00">
														</div>
				
													</div>
													<div class="col-sm-6">
														<div class="form-group">
															<label>Color Picker (RGBA)</label>
															<input class="form-control" id="colorpicker-2" type="text" value="rgba(0,194,255,0.78)" data-color-format="rgba">
														</div>
													</div>
												</div>
				
											</fieldset>
				
											<fieldset>
												<legend>
													Tags
												</legend>
				
												<div class="row">
													
													<div class="col-sm-12">
														<div class="form-group">
															<label>Type and enter to add tag</label>
															<input class="form-control tagsinput" value="Amsterdam,Washington,Sydney,Beijing,Cairo" data-role="tagsinput">
														</div>
													</div>
													
												</div>
				
											</fieldset>
											
											<div class="form-actions">
												<div class="row">
													<div class="col-md-12">
														<button class="btn btn-default" type="submit">
															Cancel
														</button>
														<button class="btn btn-primary" type="submit">
															<i class="fa fa-save"></i>
															Submit
														</button>
													</div>
												</div>
											</div>
				
										</form>
				
									</div>
									<!-- end widget content -->
				
								</div>
								<!-- end widget div -->
				
							</div>
							<!-- end widget -->
				
						</article>
						<!-- END COL -->
				
						<!-- NEW COL START -->
						<article class="col-sm-12 col-md-12 col-lg-6">
				
							<!-- Widget ID (each widget will need unique ID)-->
							<div class="jarviswidget" id="wid-id-3" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-custombutton="false">
								<!-- widget options:
								usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
				
								data-widget-colorbutton="false"
								data-widget-editbutton="false"
								data-widget-togglebutton="false"
								data-widget-deletebutton="false"
								data-widget-fullscreenbutton="false"
								data-widget-custombutton="false"
								data-widget-collapsed="true"
								data-widget-sortable="false"
				
								-->
								<header>
									<span class="widget-icon"> <i class="fa fa-edit"></i> </span>
									<h2>All Masking </h2>
				
								</header>
				
								<!-- widget div-->
								<div>
				
									<!-- widget edit box -->
									<div class="jarviswidget-editbox">
										<!-- This area used as dropdown edit box -->
				
									</div>
									<!-- end widget edit box -->
				
									<!-- widget content -->
									<div class="widget-body">
										<p class="alert alert-info text-align-center">
											USAGE: &lt;input type=&quot;text&quot; <strong>data-mask=&quot;99/99/9999&quot; data-mask-placeholder= &quot;-&quot;&gt;</strong>
										</p>
				
										<form>
				
											<fieldset>
												<legend>
													Input Masking made easier!
												</legend>
				
												<div class="form-group">
													<label>Date masking</label>
													<div class="input-group">
														<input type="text" class="form-control" data-mask="99/99/9999" data-mask-placeholder= "-">
														<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
													</div>
													<p class="note">
														Data format **/**/****
													</p>
												</div>
				
												<div class="form-group">
													<label>Phone masking</label>
													<div class="input-group">
														<input type="text" class="form-control" data-mask="(999) 999-9999" data-mask-placeholder= "X">
														<span class="input-group-addon"><i class="fa fa-phone"></i></span>
													</div>
													<p class="note">
														Data format (XXX) XXX-XXXX
													</p>
												</div>
				
												<div class="form-group">
													<label>Credit card masking</label>
													<div class="input-group">
														<input type="text" class="form-control" data-mask="9999-9999-9999-9999" data-mask-placeholder= "*">
														<span class="input-group-addon"><i class="fa fa-credit-card"></i></span>
													</div>
													<p class="note">
														Data format ****-****-****-****
													</p>
												</div>
				
												<div class="form-group">
													<label>Serial number masking</label>
													<div class="input-group">
														<input type="text" class="form-control" data-mask="***-***-***-***-***-***" data-mask-placeholder= "_">
														<span class="input-group-addon"><i class="fa fa-asterisk"></i></span>
													</div>
													<p class="note">
														Data format ***-***-***-***-***-***
													</p>
												</div>
				
												<div class="form-group">
													<label>Tax ID masking</label>
													<div class="input-group">
														<input type="text" class="form-control" data-mask="99-9999999" data-mask-placeholder= "X">
														<span class="input-group-addon"><i class="fa fa-briefcase"></i></span>
													</div>
													<p class="note">
														Data format 99-9999999
													</p>
												</div>
				
												<div class="form-actions">
													<div class="row">
														<div class="col-md-12">
															<button class="btn btn-default" type="submit">
																Cancel
															</button>
															<button class="btn btn-primary" type="submit">
																<i class="fa fa-save"></i>
																Submit
															</button>
														</div>
													</div>
												</div>
				
											</fieldset>
										</form>
				
									</div>
									<!-- end widget content -->
				
								</div>
								<!-- end widget div -->
				
							</div>
							<!-- end widget -->
				
							<!-- Widget ID (each widget will need unique ID)-->
							<div class="jarviswidget jarviswidget-color-darken" id="wid-id-5" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-custombutton="false">
								<!-- widget options:
								usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
				
								data-widget-colorbutton="false"
								data-widget-editbutton="false"
								data-widget-togglebutton="false"
								data-widget-deletebutton="false"
								data-widget-fullscreenbutton="false"
								data-widget-custombutton="false"
								data-widget-collapsed="true"
								data-widget-sortable="false"
				
								-->
								<header>
									<span class="widget-icon"> <i class="fa fa-edit"></i> </span>
									<h2>JS Knob </h2>
				
								</header>
				
								<!-- widget div-->
								<div>
				
									<!-- widget edit box -->
									<div class="jarviswidget-editbox">
										<!-- This area used as dropdown edit box -->
				
									</div>
									<!-- end widget edit box -->
				
									<!-- widget content -->
									<div class="widget-body">
				
										<form>
				
											<fieldset>
												<legend>
													JS Knob Input
												</legend>
				
												<div class="knobs-demo">
				
													<div>
														<input class="knob" data-width="120" data-height="120" data-displayInput=true value="35" data-displayPrevious=true data-fgColor="#428BCA">
													</div>
				
													<div>
														<input class="knob" data-width="180" data-height="180" data-cursor=true data-fgColor="#222222" data-thickness=.3 value="29">
													</div>
				
													<div>
														<input class="knob" data-width="80" data-height="80" data-fgColor="#71843F" data-angleOffset=-125 data-angleArc=250 value="33" data-thickness=.3>
													</div>
				
												</div>
				
											</fieldset>
											<div class="form-actions">
				
												<div class="row">
													<div class="col-md-12">
														<button class="btn btn-default" type="submit">
															Cancel
														</button>
														<button class="btn btn-primary" type="submit">
															<i class="fa fa-save"></i>
															Submit
														</button>
													</div>
												</div>
											</div>
										</form>
				
									</div>
									<!-- end widget content -->
				
								</div>
								<!-- end widget div -->
				
							</div>
							<!-- end widget -->
				
						</article>
						<!-- END COL -->
				
					</div>
				
					<!-- END ROW -->
				
				</section>
				<!-- end widget grid -->

			</div>
			<!-- END MAIN CONTENT -->

		</div>
		<!-- END MAIN PANEL -->

		<!-- SHORTCUT AREA : With large tiles (activated via clicking user name tag)
		Note: These tiles are completely responsive,
		you can add as many as you like
		-->
		<div id="shortcut">
			<ul>
				<li>
					<a href="#inbox.html" class="jarvismetro-tile big-cubes bg-color-blue"> <span class="iconbox"> <i class="fa fa-envelope fa-4x"></i> <span>Mail <span class="label pull-right bg-color-darken">14</span></span> </span> </a>
				</li>
				<li>
					<a href="#calendar.html" class="jarvismetro-tile big-cubes bg-color-orangeDark"> <span class="iconbox"> <i class="fa fa-calendar fa-4x"></i> <span>Calendar</span> </span> </a>
				</li>
				<li>
					<a href="#gmap-xml.html" class="jarvismetro-tile big-cubes bg-color-purple"> <span class="iconbox"> <i class="fa fa-map-marker fa-4x"></i> <span>Maps</span> </span> </a>
				</li>
				<li>
					<a href="#invoice.html" class="jarvismetro-tile big-cubes bg-color-blueDark"> <span class="iconbox"> <i class="fa fa-book fa-4x"></i> <span>Invoice <span class="label pull-right bg-color-darken">99</span></span> </span> </a>
				</li>
				<li>
					<a href="#gallery.html" class="jarvismetro-tile big-cubes bg-color-greenLight"> <span class="iconbox"> <i class="fa fa-picture-o fa-4x"></i> <span>Gallery </span> </span> </a>
				</li>
				<li>
					<a href="javascript:void(0);" class="jarvismetro-tile big-cubes selected bg-color-pinkDark"> <span class="iconbox"> <i class="fa fa-user fa-4x"></i> <span>My Profile </span> </span> </a>
				</li>
			</ul>
		</div>
		<!-- END SHORTCUT AREA -->

		<!--================================================== -->

		<!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)-->
		<script data-pace-options='{ "restartOnRequestAfter": true }' src="js/plugin/pace/pace.min.js"></script>

		<!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
		<script>
			if (!window.jQuery) {
				document.write('<script src="js/libs/jquery-2.0.2.min.js"><\/script>');
			}
		</script>

		<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
		<script>
			if (!window.jQuery.ui) {
				document.write('<script src="js/libs/jquery-ui-1.10.3.min.js"><\/script>');
			}
		</script>

		<!-- JS TOUCH : include this plugin for mobile drag / drop touch events
		<script src="js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script> -->

		<!-- BOOTSTRAP JS -->
		<script src="js/bootstrap/bootstrap.min.js"></script>

		<!-- CUSTOM NOTIFICATION -->
		<script src="js/notification/SmartNotification.min.js"></script>

		<!-- JARVIS WIDGETS -->
		<script src="js/smartwidgets/jarvis.widget.min.js"></script>

		<!-- EASY PIE CHARTS -->
		<script src="js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>

		<!-- SPARKLINES -->
		<script src="js/plugin/sparkline/jquery.sparkline.min.js"></script>

		<!-- JQUERY VALIDATE -->
		<script src="js/plugin/jquery-validate/jquery.validate.min.js"></script>

		<!-- JQUERY MASKED INPUT -->
		<script src="js/plugin/masked-input/jquery.maskedinput.min.js"></script>

		<!-- JQUERY SELECT2 INPUT -->
		<script src="js/plugin/select2/select2.min.js"></script>

		<!-- JQUERY UI + Bootstrap Slider -->
		<script src="js/plugin/bootstrap-slider/bootstrap-slider.min.js"></script>

		<!-- browser msie issue fix -->
		<script src="js/plugin/msie-fix/jquery.mb.browser.min.js"></script>

		<!-- FastClick: For mobile devices -->
		<script src="js/plugin/fastclick/fastclick.js"></script>

		<!--[if IE 7]>

		<h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>

		<![endif]-->

		<!-- Demo purpose only -->
		<script src="js/demo.js"></script>

		<!-- MAIN APP JS FILE -->
		<script src="js/app.js"></script>

		<!-- PAGE RELATED PLUGIN(S) -->
		<script src="js/plugin/maxlength/bootstrap-maxlength.min.js"></script>
		<script src="js/plugin/bootstrap-timepicker/bootstrap-timepicker.min.js"></script>
		<script src="js/plugin/bootstrap-tags/bootstrap-tagsinput.min.js"></script>
		<script src="js/plugin/noUiSlider/jquery.nouislider.min.js"></script>
		<script src="js/plugin/ion-slider/ion.rangeSlider.min.js"></script>
		<script src="js/plugin/colorpicker/bootstrap-colorpicker.min.js"></script>
		<script src="js/plugin/knob/jquery.knob.min.js"></script>
		<script src="js/plugin/x-editable/moment.min.js"></script>
		<script src="js/plugin/x-editable/jquery.mockjax.min.js"></script>
		<script src="js/plugin/x-editable/x-editable.min.js"></script>
		<script src="js/plugin/typeahead/typeahead.min.js"></script>
		<script src="js/plugin/typeahead/typeaheadjs.min.js"></script>


		<script type="text/javascript">
		
		// DO NOT REMOVE : GLOBAL FUNCTIONS!
		
		$(document).ready(function() {
			
			pageSetUp();
			
			 // PAGE RELATED SCRIPTS
		
			 // Spinners
			$("#spinner").spinner();
			$("#spinner-decimal").spinner({
			    step: 0.01,
			    numberFormat: "n"
			});
		
			$("#spinner-currency").spinner({
			    min: 5,
			    max: 2500,
			    step: 25,
			    start: 1000,
			    numberFormat: "C"
			});
		
			 //Maxlength
			
		    $('input[maxlength]').maxlength({
		        warningClass: "label label-success",
		        limitReachedClass: "label label-important",
		    });
		
			
			 // START AND FINISH DATE
			$('#startdate').datepicker({
			    dateFormat: 'dd.mm.yy',
			    prevText: '<i class="fa fa-chevron-left"></i>',
			    nextText: '<i class="fa fa-chevron-right"></i>',
			    onSelect: function (selectedDate) {
			        $('#finishdate').datepicker('option', 'minDate', selectedDate);
			    }
			});
			$('#finishdate').datepicker({
			    dateFormat: 'dd.mm.yy',
			    prevText: '<i class="fa fa-chevron-left"></i>',
			    nextText: '<i class="fa fa-chevron-right"></i>',
			    onSelect: function (selectedDate) {
			        $('#startdate').datepicker('option', 'maxDate', selectedDate);
			    }
			});
		
			 // Date Range Picker
			$("#from").datepicker({
			    defaultDate: "+1w",
			    changeMonth: true,
			    numberOfMonths: 3,
			    prevText: '<i class="fa fa-chevron-left"></i>',
			    nextText: '<i class="fa fa-chevron-right"></i>',
			    onClose: function (selectedDate) {
			        $("#to").datepicker("option", "maxDate", selectedDate);
			    }
		
			});
			$("#to").datepicker({
			    defaultDate: "+1w",
			    changeMonth: true,
			    numberOfMonths: 3,
			    prevText: '<i class="fa fa-chevron-left"></i>',
			    nextText: '<i class="fa fa-chevron-right"></i>',
			    onClose: function (selectedDate) {
			        $("#from").datepicker("option", "minDate", selectedDate);
			    }
			});
		
			/*
			 * TIMEPICKER
			 */
		
			$('#timepicker').timepicker();
			
			/*
			 * JS SLIDER
			 */
		
		    $("#nouislider-1").noUiSlider({
		        range: [2, 100],
		        start: 55,
		        handles: 1,
		        connect: true,
		    });
		
		    $("#nouislider-2").noUiSlider({
		        range: [0, 300],
		        start: [55, 130],
		        step: 60,
		        handles: 2,
		        connect: true
		    });
		
		    $("#nouislider-3").noUiSlider({
		        range: [0, 1000],
		        start: [264, 776],
		        step: 1,
		        connect: true,
		        slide: function () {
		            var values = $(this).val();
		            $(".nouislider-value").text(values[0] + " - " + values[1]);
		        }
		    });
		
		    $("#nouislider-4").noUiSlider({
		        range: [0, 100],
		        start: 50,
		        handles: 1
		    }).attr("disabled", "disabled");
		
		
		
			/*
			 * ION SLIDER
			 */
		
		    $("#range-slider-1").ionRangeSlider({
		        min: 0,
		        max: 5000,
		        from: 1000,
		        to: 4000,
		        type: 'double',
		        step: 1,
		        prefix: "$",
		        prettify: false,
		        hasGrid: true
		    });
		
		    $("#range-slider-2").ionRangeSlider();
		
		    $("#range-slider-3").ionRangeSlider({
		        min: 0,
		        from: 2.3,
		        max: 10,
		        type: 'single',
		        step: 0.1,
		        postfix: " mm",
		        prettify: false,
		        hasGrid: true
		    });
		
		    $("#range-slider-4").ionRangeSlider({
		        min: -50,
		        max: 50,
		        from: 5,
		        to: 25,
		        type: 'double',
		        step: 1,
		        postfix: "°",
		        prettify: false,
		        hasGrid: true
		    });
		
		    $("#range-slider-5").ionRangeSlider({
		        min: 0,
		        from: 0,
		        max: 10,
		        type: 'single',
		        step: 0.1,
		        postfix: " mm",
		        prettify: false,
		        hasGrid: true
		    });
		
		
		
			/*
			 * COLOR PICKER
			 */
		
		    $('#colorpicker-1').colorpicker()
		    $('#colorpicker-2').colorpicker()
		
		
		
			/*
			 * KNOB
			 */
		
		    $('.knob').knob({
		        change: function (value) {
		            //console.log("change : " + value);
		        },
		        release: function (value) {
		            //console.log(this.$.attr('value'));
		            //console.log("release : " + value);
		        },
		        cancel: function () {
		            //console.log("cancel : ", this);
		        }
		    });
		
		
			/*
			 * X-Ediable
			 */
		
		
		
		
		    (function (e) {
		        "use strict";
		        var t = function (e) {
		            this.init("address", e, t.defaults)
		        };
		        e.fn.editableutils.inherit(t, e.fn.editabletypes.abstractinput);
		        e.extend(t.prototype, {
		            render: function () {
		                this.$input = this.$tpl.find("input")
		            },
		            value2html: function (t, n) {
		                if (!t) {
		                    e(n).empty();
		                    return
		                }
		                var r = e("<div>").text(t.city).html() + ", " + e("<div>").text(t.street).html() +
		                    " st., bld. " + e("<div>").text(t.building).html();
		                e(n).html(r)
		            },
		            html2value: function (e) {
		                return null
		            },
		            value2str: function (e) {
		                var t = "";
		                if (e)
		                    for (var n in e)
		                        t = t + n + ":" + e[n] + ";";
		                return t
		            },
		            str2value: function (e) {
		                return e
		            },
		            value2input: function (e) {
		                if (!e)
		                    return;
		                this.$input.filter('[name="city"]').val(e.city);
		                this.$input.filter('[name="street"]').val(e.street);
		                this.$input.filter('[name="building"]').val(e.building)
		            },
		            input2value: function () {
		                return {
		                    city: this.$input.filter('[name="city"]').val(),
		                    street: this.$input.filter('[name="street"]').val(),
		                    building: this.$input.filter('[name="building"]').val()
		                }
		            },
		            activate: function () {
		                this.$input.filter('[name="city"]').focus()
		            },
		            autosubmit: function () {
		                this.$input.keydown(function (t) {
		                    t.which === 13 && e(this).closest("form").submit()
		                })
		            }
		        });
		        t.defaults = e.extend({}, e.fn.editabletypes.abstractinput.defaults, {
		            tpl: '<div class="editable-address"><label><span>City: </span><input type="text" name="city" class="input-small"></label></div><div class="editable-address"><label><span>Street: </span><input type="text" name="street" class="input-small"></label></div><div class="editable-address"><label><span>Building: </span><input type="text" name="building" class="input-mini"></label></div>',
		            inputclass: ""
		        });
		        e.fn.editabletypes.address = t
		    })(window.jQuery);
		
		    //ajax mocks
		    $.mockjaxSettings.responseTime = 500;
		
		    $.mockjax({
		        url: '/post',
		        response: function (settings) {
		            log(settings, this);
		        }
		    });
		
		    $.mockjax({
		        url: '/error',
		        status: 400,
		        statusText: 'Bad Request',
		        response: function (settings) {
		            this.responseText = 'Please input correct value';
		            log(settings, this);
		        }
		    });
		
		    $.mockjax({
		        url: '/status',
		        status: 500,
		        response: function (settings) {
		            this.responseText = 'Internal Server Error';
		            log(settings, this);
		        }
		    });
		
		    $.mockjax({
		        url: '/groups',
		        response: function (settings) {
		            this.responseText = [{
		                value: 0,
		                text: 'Guest'
		            }, {
		                value: 1,
		                text: 'Service'
		            }, {
		                value: 2,
		                text: 'Customer'
		            }, {
		                value: 3,
		                text: 'Operator'
		            }, {
		                value: 4,
		                text: 'Support'
		            }, {
		                value: 5,
		                text: 'Admin'
		            }];
		            log(settings, this);
		        }
		    });
		
		    //TODO: add this div to page
		    function log(settings, response) {
		        var s = [],
		            str;
		        s.push(settings.type.toUpperCase() + ' url = "' + settings.url + '"');
		        for (var a in settings.data) {
		            if (settings.data[a] && typeof settings.data[a] === 'object') {
		                str = [];
		                for (var j in settings.data[a]) {
		                    str.push(j + ': "' + settings.data[a][j] + '"');
		                }
		                str = '{ ' + str.join(', ') + ' }';
		            } else {
		                str = '"' + settings.data[a] + '"';
		            }
		            s.push(a + ' = ' + str);
		        }
		        s.push('RESPONSE: status = ' + response.status);
		
		        if (response.responseText) {
		            if ($.isArray(response.responseText)) {
		                s.push('[');
		                $.each(response.responseText, function (i, v) {
		                    s.push('{value: ' + v.value + ', text: "' + v.text + '"}');
		                });
		                s.push(']');
		            } else {
		                s.push($.trim(response.responseText));
		            }
		        }
		        s.push('--------------------------------------\n');
		        $('#console').val(s.join('\n') + $('#console').val());
		    }
		
		    /*
		     * X-EDITABLES
		     */
		
		    $('#inline').on('change', function (e) {
		        if ($(this).prop('checked')) {
		            window.location.href = '?mode=inline#ajax/plugins.html';
		        } else {
		            window.location.href = '?#ajax/plugins.html';
		        }
		    });
		
		    if (window.location.href.indexOf("?mode=inline") > -1) {
		        $('#inline').prop('checked', true);
		        $.fn.editable.defaults.mode = 'inline';
		    } else {
		        $('#inline').prop('checked', false);
		        $.fn.editable.defaults.mode = 'popup';
		    }
		
		    //defaults
		    $.fn.editable.defaults.url = '/post';
		    //$.fn.editable.defaults.mode = 'inline'; use this to edit inline
		
		    //enable / disable
		    $('#enable').click(function () {
		        $('#user .editable').editable('toggleDisabled');
		    });
		
		    //editables
		    $('#username').editable({
		        url: '/post',
		        type: 'text',
		        pk: 1,
		        name: 'username',
		        title: 'Enter username'
		    });
		
		    $('#firstname').editable({
		        validate: function (value) {
		            if ($.trim(value) == '')
		                return 'This field is required';
		        }
		    });
		
		    $('#sex').editable({
		        prepend: "not selected",
		        source: [{
		            value: 1,
		            text: 'Male'
		        }, {
		            value: 2,
		            text: 'Female'
		        }],
		        display: function (value, sourceData) {
		            var colors = {
		                "": "gray",
		                1: "green",
		                2: "blue"
		            }, elem = $.grep(sourceData, function (o) {
		                    return o.value == value;
		                });
		
		            if (elem.length) {
		                $(this).text(elem[0].text).css("color", colors[value]);
		            } else {
		                $(this).empty();
		            }
		        }
		    });
		
		    $('#status').editable();
		
		    $('#group').editable({
		        showbuttons: false
		    });
		
		    $('#vacation').editable({
		        datepicker: {
		            todayBtn: 'linked'
		        }
		    });
		
		    $('#dob').editable();
		
		    $('#event').editable({
		        placement: 'right',
		        combodate: {
		            firstItem: 'name'
		        }
		    });
		
		    $('#meeting_start').editable({
		        format: 'yyyy-mm-dd hh:ii',
		        viewformat: 'dd/mm/yyyy hh:ii',
		        validate: function (v) {
		            if (v && v.getDate() == 10)
		                return 'Day cant be 10!';
		        },
		        datetimepicker: {
		            todayBtn: 'linked',
		            weekStart: 1
		        }
		    });
		
		    $('#comments').editable({
		        showbuttons: 'bottom'
		    });
		
		    $('#note').editable();
		    $('#pencil').click(function (e) {
		        e.stopPropagation();
		        e.preventDefault();
		        $('#note').editable('toggle');
		    });
		
		    $('#state').editable({
		        source: ["Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut",
		            "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas",
		            "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota",
		            "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey",
		            "New Mexico", "New York", "North Dakota", "North Carolina", "Ohio", "Oklahoma", "Oregon",
		            "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas",
		            "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"
		        ]
		    });
		
		    $('#state2').editable({
		        value: 'California',
		        typeahead: {
		            name: 'state',
		            local: ["Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut",
		                "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa",
		                "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan",
		                "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire",
		                "New Jersey", "New Mexico", "New York", "North Dakota", "North Carolina", "Ohio",
		                "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota",
		                "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia",
		                "Wisconsin", "Wyoming"
		            ]
		        }
		    });
		
		    $('#fruits').editable({
		        pk: 1,
		        limit: 3,
		        source: [{
		            value: 1,
		            text: 'banana'
		        }, {
		            value: 2,
		            text: 'peach'
		        }, {
		            value: 3,
		            text: 'apple'
		        }, {
		            value: 4,
		            text: 'watermelon'
		        }, {
		            value: 5,
		            text: 'orange'
		        }]
		    });
		
		    $('#tags').editable({
		        inputclass: 'input-large',
		        select2: {
		            tags: ['html', 'javascript', 'css', 'ajax'],
		            tokenSeparators: [",", " "]
		        }
		    });
		
		    var countries = [];
		    $.each({
		        "BD": "Bangladesh",
		        "BE": "Belgium",
		        "BF": "Burkina Faso",
		        "BG": "Bulgaria",
		        "BA": "Bosnia and Herzegovina",
		        "BB": "Barbados",
		        "WF": "Wallis and Futuna",
		        "BL": "Saint Bartelemey",
		        "BM": "Bermuda",
		        "BN": "Brunei Darussalam",
		        "BO": "Bolivia",
		        "BH": "Bahrain",
		        "BI": "Burundi",
		        "BJ": "Benin",
		        "BT": "Bhutan",
		        "JM": "Jamaica",
		        "BV": "Bouvet Island",
		        "BW": "Botswana",
		        "WS": "Samoa",
		        "BR": "Brazil",
		        "BS": "Bahamas",
		        "JE": "Jersey",
		        "BY": "Belarus",
		        "O1": "Other Country",
		        "LV": "Latvia",
		        "RW": "Rwanda",
		        "RS": "Serbia",
		        "TL": "Timor-Leste",
		        "RE": "Reunion",
		        "LU": "Luxembourg",
		        "TJ": "Tajikistan",
		        "RO": "Romania",
		        "PG": "Papua New Guinea",
		        "GW": "Guinea-Bissau",
		        "GU": "Guam",
		        "GT": "Guatemala",
		        "GS": "South Georgia and the South Sandwich Islands",
		        "GR": "Greece",
		        "GQ": "Equatorial Guinea",
		        "GP": "Guadeloupe",
		        "JP": "Japan",
		        "GY": "Guyana",
		        "GG": "Guernsey",
		        "GF": "French Guiana",
		        "GE": "Georgia",
		        "GD": "Grenada",
		        "GB": "United Kingdom",
		        "GA": "Gabon",
		        "SV": "El Salvador",
		        "GN": "Guinea",
		        "GM": "Gambia",
		        "GL": "Greenland",
		        "GI": "Gibraltar",
		        "GH": "Ghana",
		        "OM": "Oman",
		        "TN": "Tunisia",
		        "JO": "Jordan",
		        "HR": "Croatia",
		        "HT": "Haiti",
		        "HU": "Hungary",
		        "HK": "Hong Kong",
		        "HN": "Honduras",
		        "HM": "Heard Island and McDonald Islands",
		        "VE": "Venezuela",
		        "PR": "Puerto Rico",
		        "PS": "Palestinian Territory",
		        "PW": "Palau",
		        "PT": "Portugal",
		        "SJ": "Svalbard and Jan Mayen",
		        "PY": "Paraguay",
		        "IQ": "Iraq",
		        "PA": "Panama",
		        "PF": "French Polynesia",
		        "BZ": "Belize",
		        "PE": "Peru",
		        "PK": "Pakistan",
		        "PH": "Philippines",
		        "PN": "Pitcairn",
		        "TM": "Turkmenistan",
		        "PL": "Poland",
		        "PM": "Saint Pierre and Miquelon",
		        "ZM": "Zambia",
		        "EH": "Western Sahara",
		        "RU": "Russian Federation",
		        "EE": "Estonia",
		        "EG": "Egypt",
		        "TK": "Tokelau",
		        "ZA": "South Africa",
		        "EC": "Ecuador",
		        "IT": "Italy",
		        "VN": "Vietnam",
		        "SB": "Solomon Islands",
		        "EU": "Europe",
		        "ET": "Ethiopia",
		        "SO": "Somalia",
		        "ZW": "Zimbabwe",
		        "SA": "Saudi Arabia",
		        "ES": "Spain",
		        "ER": "Eritrea",
		        "ME": "Montenegro",
		        "MD": "Moldova, Republic of",
		        "MG": "Madagascar",
		        "MF": "Saint Martin",
		        "MA": "Morocco",
		        "MC": "Monaco",
		        "UZ": "Uzbekistan",
		        "MM": "Myanmar",
		        "ML": "Mali",
		        "MO": "Macao",
		        "MN": "Mongolia",
		        "MH": "Marshall Islands",
		        "MK": "Macedonia",
		        "MU": "Mauritius",
		        "MT": "Malta",
		        "MW": "Malawi",
		        "MV": "Maldives",
		        "MQ": "Martinique",
		        "MP": "Northern Mariana Islands",
		        "MS": "Montserrat",
		        "MR": "Mauritania",
		        "IM": "Isle of Man",
		        "UG": "Uganda",
		        "TZ": "Tanzania, United Republic of",
		        "MY": "Malaysia",
		        "MX": "Mexico",
		        "IL": "Israel",
		        "FR": "France",
		        "IO": "British Indian Ocean Territory",
		        "FX": "France, Metropolitan",
		        "SH": "Saint Helena",
		        "FI": "Finland",
		        "FJ": "Fiji",
		        "FK": "Falkland Islands (Malvinas)",
		        "FM": "Micronesia, Federated States of",
		        "FO": "Faroe Islands",
		        "NI": "Nicaragua",
		        "NL": "Netherlands",
		        "NO": "Norway",
		        "NA": "Namibia",
		        "VU": "Vanuatu",
		        "NC": "New Caledonia",
		        "NE": "Niger",
		        "NF": "Norfolk Island",
		        "NG": "Nigeria",
		        "NZ": "New Zealand",
		        "NP": "Nepal",
		        "NR": "Nauru",
		        "NU": "Niue",
		        "CK": "Cook Islands",
		        "CI": "Cote d'Ivoire",
		        "CH": "Switzerland",
		        "CO": "Colombia",
		        "CN": "China",
		        "CM": "Cameroon",
		        "CL": "Chile",
		        "CC": "Cocos (Keeling) Islands",
		        "CA": "Canada",
		        "CG": "Congo",
		        "CF": "Central African Republic",
		        "CD": "Congo, The Democratic Republic of the",
		        "CZ": "Czech Republic",
		        "CY": "Cyprus",
		        "CX": "Christmas Island",
		        "CR": "Costa Rica",
		        "CV": "Cape Verde",
		        "CU": "Cuba",
		        "SZ": "Swaziland",
		        "SY": "Syrian Arab Republic",
		        "KG": "Kyrgyzstan",
		        "KE": "Kenya",
		        "SR": "Suriname",
		        "KI": "Kiribati",
		        "KH": "Cambodia",
		        "KN": "Saint Kitts and Nevis",
		        "KM": "Comoros",
		        "ST": "Sao Tome and Principe",
		        "SK": "Slovakia",
		        "KR": "Korea, Republic of",
		        "SI": "Slovenia",
		        "KP": "Korea, Democratic People's Republic of",
		        "KW": "Kuwait",
		        "SN": "Senegal",
		        "SM": "San Marino",
		        "SL": "Sierra Leone",
		        "SC": "Seychelles",
		        "KZ": "Kazakhstan",
		        "KY": "Cayman Islands",
		        "SG": "Singapore",
		        "SE": "Sweden",
		        "SD": "Sudan",
		        "DO": "Dominican Republic",
		        "DM": "Dominica",
		        "DJ": "Djibouti",
		        "DK": "Denmark",
		        "VG": "Virgin Islands, British",
		        "DE": "Germany",
		        "YE": "Yemen",
		        "DZ": "Algeria",
		        "US": "United States",
		        "UY": "Uruguay",
		        "YT": "Mayotte",
		        "UM": "United States Minor Outlying Islands",
		        "LB": "Lebanon",
		        "LC": "Saint Lucia",
		        "LA": "Lao People's Democratic Republic",
		        "TV": "Tuvalu",
		        "TW": "Taiwan",
		        "TT": "Trinidad and Tobago",
		        "TR": "Turkey",
		        "LK": "Sri Lanka",
		        "LI": "Liechtenstein",
		        "A1": "Anonymous Proxy",
		        "TO": "Tonga",
		        "LT": "Lithuania",
		        "A2": "Satellite Provider",
		        "LR": "Liberia",
		        "LS": "Lesotho",
		        "TH": "Thailand",
		        "TF": "French Southern Territories",
		        "TG": "Togo",
		        "TD": "Chad",
		        "TC": "Turks and Caicos Islands",
		        "LY": "Libyan Arab Jamahiriya",
		        "VA": "Holy See (Vatican City State)",
		        "VC": "Saint Vincent and the Grenadines",
		        "AE": "United Arab Emirates",
		        "AD": "Andorra",
		        "AG": "Antigua and Barbuda",
		        "AF": "Afghanistan",
		        "AI": "Anguilla",
		        "VI": "Virgin Islands, U.S.",
		        "IS": "Iceland",
		        "IR": "Iran, Islamic Republic of",
		        "AM": "Armenia",
		        "AL": "Albania",
		        "AO": "Angola",
		        "AN": "Netherlands Antilles",
		        "AQ": "Antarctica",
		        "AP": "Asia/Pacific Region",
		        "AS": "American Samoa",
		        "AR": "Argentina",
		        "AU": "Australia",
		        "AT": "Austria",
		        "AW": "Aruba",
		        "IN": "India",
		        "AX": "Aland Islands",
		        "AZ": "Azerbaijan",
		        "IE": "Ireland",
		        "ID": "Indonesia",
		        "UA": "Ukraine",
		        "QA": "Qatar",
		        "MZ": "Mozambique"
		    }, function (k, v) {
		        countries.push({
		            id: k,
		            text: v
		        });
		    });
		
		    $('#country').editable({
		        source: countries,
		        select2: {
		            width: 200
		        }
		    });
		
		    $('#address').editable({
		        url: '/post',
		        value: {
		            city: "Moscow",
		            street: "Lenina",
		            building: "12"
		        },
		        validate: function (value) {
		            if (value.city == '')
		                return 'city is required!';
		        },
		        display: function (value) {
		            if (!value) {
		                $(this).empty();
		                return;
		            }
		            var html = '<b>' + $('<div>').text(value.city).html() + '</b>, ' + $('<div>').text(value.street)
		                .html() + ' st., bld. ' + $('<div>').text(value.building).html();
		            $(this).html(html);
		        }
		    });
		
		    $('#user .editable').on('hidden', function (e, reason) {
		        if (reason === 'save' || reason === 'nochange') {
		            var $next = $(this).closest('tr').next().find('.editable');
		            if ($('#autoopen').is(':checked')) {
		                setTimeout(function () {
		                    $next.editable('show');
		                }, 300);
		            } else {
		                $next.focus();
		            }
		        }
		    });			
	
		})

		</script>

		<!-- Your GOOGLE ANALYTICS CODE Below -->
		<script type="text/javascript">
			var _gaq = _gaq || [];
				_gaq.push(['_setAccount', 'UA-XXXXXXXX-X']);
				_gaq.push(['_trackPageview']);
			
			(function() {
				var ga = document.createElement('script');
				ga.type = 'text/javascript';
				ga.async = true;
				ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
				var s = document.getElementsByTagName('script')[0];
				s.parentNode.insertBefore(ga, s);
			})();

		</script>

	</body>

</html>