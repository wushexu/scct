/*  
 *   Copyright 2012 OSBI Ltd
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

/**
 * Dialog for member selections
 */
var DrillthroughModal = Modal.extend({
    type: "drillthrough",

    buttons: [
        { text: "Ok", method: "ok" },
        { text: "Cancel", method: "close" }
    ],

    events: {
        'click .collapsed': 'select',
        'click .expand': 'select',
        'click .folder_collapsed': 'select',
        'click .folder_expanded': 'select',
        'click .dialog_footer a' : 'call',
        'click .parent_dimension input' : 'select_dimension',
        'click .measure_tree input' : 'select_measure',
        'click input.all_measures' : 'select_all_measures',
        'click input.all_dimensions' : 'select_all_dimensions'
    },

    allMeasures: false,

    templateContent: function() {
        return $("#template-drillthrough").html();
    },



    initialize: function(args) {
        // Initialize properties
        _.extend(this, args);
        this.options.title = args.title;
        this.query = args.workspace.query;

        this.position = args.position;
        this.action = args.action;
        Saiku.ui.unblock();
        _.bindAll(this, "ok", "drilled", "template");

        // Resize when rendered

        this.render();
        // Load template
        $(this.el).find('.dialog_body')
            .html(_.template(this.templateContent())(this));
        // Show dialog
        $(this.el).find('.maxrows').val(this.maxrows);

        var schema = this.query.get('schema');
        var cube = this.query.get('cube');
        var key = this.query.get('connection') + "/" +
            this.query.get('catalog') + "/"
            + ((schema == "" || schema == null) ? "null" : schema)
            + "/" + cube;

        var cubeModel = Saiku.session.sessionworkspace.cube[key];
        var dimensions = null;
        var measures = null;
        var drillDeliveryOrders = false;

        if(this.type == 'drillthrough' && schema == 'Delivery Order'){
        	drillDeliveryOrders = true;
        }

        if (cubeModel && cubeModel.has('data')) {
            dimensions = cubeModel.get('data').dimensions;
            measures = cubeModel.get('data').measures;
        }

        if (!cubeModel || !dimensions || !measures) {
            if (typeof localStorage !== "undefined" && localStorage && localStorage.getItem("cube." + key) !== null) {
                Saiku.session.sessionworkspace.cube[key] = new Cube(JSON.parse(localStorage.getItem("cube." + key)));
            } else {
                Saiku.session.sessionworkspace.cube[key] = new Cube({ key: key });
                Saiku.session.sessionworkspace.cube[key].fetch({ async : false });
            }
            dimensions = Saiku.session.sessionworkspace.cube[key].get('data').dimensions;
            measures = Saiku.session.sessionworkspace.cube[key].get('data').measures;
        }

        var templ_dim,templ_measure,templ_deliveryOrder,templ_deliveryOrderItem,templ_handlingUnit;
        var container;
        if(drillDeliveryOrders){
        	container = $("#template-drillthrough-do-list").html();
        	
        	var doDim={
        		name: 'Delivery Order',
        		collapsed: false,
                fields: [
	                {name:'do.id',caption:"id"},
	                {name:'do.dlvryNum',caption:"dlvryNum",checked:true},
	                {name:'do.preCarrier',caption:"1st Leg Carrier Code"},
	                {name:'do.carrCode',caption:"2nd Leg Carrier Code"},
	                {name:'do.carrName',caption:"2nd Leg Carrier Name"},
	                {name:'do.carrPickupDate',caption:"carrPickupDate"},
	                {name:'do.cdd',caption:"cdd",checked:true},
	                {name:'do.containerNumber',caption:"containerNumber"},
	                {name:'do.containerSize',caption:"containerSize"},
	                {name:'do.createdDate',caption:"createdDate"},
	                {name:'do.lastUpdateDate',caption:"lastUpdateDate"},
	                {name:'do.modifiedDate',caption:"modifiedDate"},
	                {name:'do.departurePort',caption:"departurePort"},
	                {name:'do.deptDate',caption:"deptDate"},
	                {name:'do.destinationPort',caption:"destinationPort"},
	                {name:'do.dlvryItemQty',caption:"dlvryItemQty"},
	                {name:'do.estArrDate',caption:"estArrDate"},
	                {name:'do.exportCustomGate',caption:"exportCustomGate"},
	                {name:'do.hawbHswb',caption:"hawbHswb"},
	                {name:'do.idocCreatedDate',caption:"idocCreatedDate"},
	                {name:'do.idocNum',caption:"idocNum"},
	                {name:'do.mainCarrier',caption:"mainCarrier"},
	                {name:'do.modeOfTrspn',caption:"modeOfTrspn"},
	                {name:'do.statusCode',caption:"statusCode"},
	                {name:'do.orderMilestone',caption:"orderMilestone"},
	                {name:'do.orderMilestoneName',caption:"orderMilestoneName"},
	                {name:'do.packDate',caption:"packDate"},
	                {name:'do.podDate',caption:"podDate",checked:true},
	                {name:'do.podEntryDate',caption:"podEntryDate",checked:true},
	                {name:'do.rte',caption:"rte",checked:true},
	                {name:'do.serviceLevel',caption:"serviceLevel",checked:true},
	                {name:'do.shipToCtryCd',caption:"shipToCtryCd",checked:true},
	                {name:'do.shipToCtryNm',caption:"shipToCtryNm",checked:true},
	                {name:'country.ctryRegion',caption:"ctryRegion",checked:true},
	                {name:'country.subGeo',caption:"subGeo",checked:true},
	                {name:'do.shippedQty',caption:"shippedQty"},
	                {name:'do.shpngCode',caption:"shpngCode",checked:true},
	                {name:'do.shpngSrc',caption:"shpngSrc"},
	                {name:'do.slsOrderNum',caption:"slsOrderNum"},
	                {name:'do.sosDlvryNum',caption:"sosDlvryNum"},
	                {name:'do.sosOrderNum',caption:"sosOrderNum"},
	                {name:'do.subMilestone',caption:"subMilestone"},
	                {name:'do.subMilestoneName',caption:"subMilestoneName"},
	                {name:'do.truck',caption:"truck"},
	                {name:'do.bol',caption:"bol"},
	                {name:'do.statusTimeOf01',caption:'statusTimeOf01'},
	                {name:'do.statusTimeOfK',caption:'statusTimeOfK'},
	                {name:'do.statusTimeOfBF',caption:'statusTimeOfBF'},
	                {name:'do.statusTimeOfH1',caption:'statusTimeOfH1'},
	                {name:'do.statusTimeOfH2',caption:'statusTimeOfH2'},
	                {name:'do.statusTimeOfAF',caption:'statusTimeOfAF'},
	                {name:'do.statusTimeOfC',caption:'statusTimeOfC'},
	                {name:'do.statusTimeOfE',caption:'statusTimeOfE'},
	                {name:'do.statusTimeOfF',caption:'statusTimeOfF'},
	                {name:'do.statusTimeOfA',caption:'statusTimeOfA'},
	                {name:'do.statusTimeOfCT',caption:'statusTimeOfCT'},
	                {name:'do.statusTimeOfOA',caption:'statusTimeOfOA'},
	                {name:'do.statusTimeOfD',caption:'statusTimeOfD'},
	                {name:'do.statusTimeOfJ',caption:'statusTimeOfJ'},
	                {name:'do.statusTimeOfP',caption:'statusTimeOfP'},
	                {name:'do.statusTimeOfOH',caption:'statusTimeOfOH'},
	                {name:'do.statusTimeOfX1',caption:'statusTimeOfX1'},
	                {name:'do.reasonCodeOf01',caption:'reasonCodeOf01'},
	                {name:'do.reasonCodeOfK',caption:'reasonCodeOfK'},
	                {name:'do.reasonCodeOfBF',caption:'reasonCodeOfBF'},
	                {name:'do.reasonCodeOfH1',caption:'reasonCodeOfH1'},
	                {name:'do.reasonCodeOfH2',caption:'reasonCodeOfH2'},
	                {name:'do.reasonCodeOfAF',caption:'reasonCodeOfAF'},
	                {name:'do.reasonCodeOfC',caption:'reasonCodeOfC'},
	                {name:'do.reasonCodeOfE',caption:'reasonCodeOfE'},
	                {name:'do.reasonCodeOfF',caption:'reasonCodeOfF'},
	                {name:'do.reasonCodeOfA',caption:'reasonCodeOfA'},
	                {name:'do.reasonCodeOfCT',caption:'reasonCodeOfCT'},
	                {name:'do.reasonCodeOfOA',caption:'reasonCodeOfOA'},
	                {name:'do.reasonCodeOfD',caption:'reasonCodeOfD'},
	                {name:'do.reasonCodeOfJ',caption:'reasonCodeOfJ'},
	                {name:'do.reasonCodeOfP',caption:'reasonCodeOfP'},
	                {name:'do.reasonCodeOfOH',caption:'reasonCodeOfOH'},
	                {name:'do.reasonCodeOfX1',caption:'reasonCodeOfX1'},
	                {name:'do.entryTimeOf01',caption:'entryTimeOf01'},
	                {name:'do.entryTimeOfK',caption:'entryTimeOfK'},
	                {name:'do.entryTimeOfBF',caption:'entryTimeOfBF'},
	                {name:'do.entryTimeOfH1',caption:'entryTimeOfH1'},
	                {name:'do.entryTimeOfH2',caption:'entryTimeOfH2'},
	                {name:'do.entryTimeOfAF',caption:'entryTimeOfAF'},
	                {name:'do.entryTimeOfC',caption:'entryTimeOfC'},
	                {name:'do.entryTimeOfE',caption:'entryTimeOfE'},
	                {name:'do.entryTimeOfF',caption:'entryTimeOfF'},
	                {name:'do.entryTimeOfA',caption:'entryTimeOfA'},
	                {name:'do.entryTimeOfCT',caption:'entryTimeOfCT'},
	                {name:'do.entryTimeOfOA',caption:'entryTimeOfOA'},
	                {name:'do.entryTimeOfD',caption:'entryTimeOfD'},
	                {name:'do.entryTimeOfJ',caption:'entryTimeOfJ'},
	                {name:'do.entryTimeOfP',caption:'entryTimeOfP'},
	                {name:'do.entryTimeOfOH',caption:'entryTimeOfOH'},
	                {name:'do.entryTimeOfX1',caption:'entryTimeOfX1'},
	                {name:'do.dk01Color',caption:"KPI-CDD"},
	                {name:'do.dk02Color',caption:"KPI-IOD"},
	                {name:'do.dk03Color',caption:"KPI-M3.5"},
	                {name:'do.dk04Color',caption:"KPI-M4.5"},
	                {name:'do.dk05Color',caption:"KPI-TOTH"}
	            ]
        	};
            templ_deliveryOrder =_.template($("#template-drillthrough-deliveryOrders").html())({dimension: doDim});
            

        	var doItemDim={
        		name: 'Delivery Order Item',
        		collapsed: true,
                fields: [
	                {name:'item.id',caption:"id"},
	                {name:'item.dlvryNum',caption:"dlvryNum"},
	                {name:'item.dlvryItemNum',caption:"dlvryItemNum"},
	                {name:'item.dlvrdQty',caption:"dlvrdQty"},
	                {name:'item.carrCode',caption:"carrCode"},
	                {name:'item.carrName',caption:"carrName"},
	                {name:'item.carrPickupDate',caption:"carrPickupDate"},
	                {name:'item.modeOfTrspn',caption:"modeOfTrspn"},
	                {name:'item.rte',caption:"rte"},
	                {name:'item.shipToCtryCd',caption:"shipToCtryCd"},
	                {name:'item.shipToCtryNm',caption:"shipToCtryNm"},
	                {name:'item.shpngSrc',caption:"shpngSrc"},
	                {name:'item.createdDate',caption:"createdDate"},
	                {name:'item.modifiedDate',caption:"modifiedDate"},
	                {name:'item.estArrDate',caption:"estArrDate"},
	                {name:'item.idocNum',caption:"idocNum"},
	                {name:'item.idocCreatedDate',caption:"idocCreatedDate"},
	                {name:'item.lastUpdateDate',caption:"lastUpdateDate"},
	                {name:'item.deptDate',caption:"deptDate"},
	                {name:'item.podEntryDate',caption:"podEntryDate"},
	                {name:'item.cdd',caption:"cdd"},
	                {name:'item.podDate',caption:"podDate"},
	                {name:'item.orderMilestone',caption:"orderMilestone"},
	                {name:'item.orderMilestoneName',caption:"orderMilestoneName"},
	                {name:'item.packDate',caption:"packDate"},
	                {name:'item.pickDate',caption:"pickDate"},
	                {name:'item.slsOrdrNum',caption:"slsOrdrNum"},
	                {name:'item.slsOrdrLineNum',caption:"slsOrdrLineNum"},
	                {name:'item.productCode',caption:"productCode"},
	                {name:'item.productDesc',caption:"productDesc"},
	                {name:'item.matlGrp1',caption:"matlGrp1"},
	                {name:'item.orderType',caption:"orderType"},
	                {name:'item.dk01Color',caption:"KPI-CDD"},
	                {name:'item.dk02Color',caption:"KPI-IOD"},
	                {name:'item.dk03Color',caption:"KPI-M3.5"},
	                {name:'item.dk04Color',caption:"KPI-M4.5"},
	                {name:'item.dk05Color',caption:"KPI-TOTH"},
	                {name:'item.bol',caption:"bol"},
	                {name:'item.partNum',caption:"partNum"},
	                {name:'item.soldToCtryCd',caption:"soldToCtryCd"},
	                {name:'item.poNum',caption:"poNum"},
	                {name:'item.poItemNum',caption:"poItemNum"}
                ]
        	};
            templ_deliveryOrderItem =_.template($("#template-drillthrough-deliveryOrders").html())({dimension: doItemDim});
            

        	var doHU={
        		name: 'Handling Unit',
        		collapsed: true,
                fields: [
                     {name:'hu.id',caption:"id"},
                     {name:'hu.HU_OID',caption:"HU_ID"},
                     {name:'hu.sscc',caption:"sscc"},
                     {name:'hu.dlvryNum',caption:"dlvryNum"},
                     {name:'hu.dlvryItemNum',caption:"dlvryItemNum"},
                     {name:'hu.netWeight',caption:"netWeight"},
                     {name:'hu.weightUnitTare',caption:"weightUnitTare"},
                     {name:'hu.totalWeight',caption:"totalWeight"},
                     {name:'hu.weightUnit',caption:"weightUnit"},
                     {name:'hu.length',caption:"length"},
                     {name:'hu.width',caption:"width"},
                     {name:'hu.height',caption:"height"},
                     {name:'hu.unitOfDimension',caption:"unitOfDimension"},
                     {name:'hu.totalVolume',caption:"totalVolume"},
                     {name:'hu.volumeUnit',caption:"volumeUnit"},
                     {name:'hu.tareVolume',caption:"tareVolume"},
                     {name:'hu.volumeUnitTare',caption:"volumeUnitTare"},
                     {name:'hu.packedQty',caption:"packedQty"},
                     {name:'hu.materialNumber',caption:"materialNumber"},
                     {name:'hu.idocNum',caption:"idocNum"},
                     {name:'hu.idocCreatedDate',caption:"idocCreatedDate"}
                ]
        	};
        	templ_handlingUnit =_.template($("#template-drillthrough-deliveryOrders").html())({dimension: doHU});
            
        }else{
        	container = $("#template-drillthrough-list").html();
        	
            templ_dim =_.template($("#template-drillthrough-dimensions").html())({dimensions: dimensions});
            templ_measure =_.template($("#template-drillthrough-measures").html())({measures: measures, allMeasures: this.allMeasures});
        }

        $(container).appendTo($(this.el).find('.dialog_body'));
        $(this.el).find('.sidebar').height(($("body").height() / 2) + ($("body").height() / 6) );
        $(this.el).find('.sidebar').width(380);

        if(drillDeliveryOrders){
        	var treeHtml=templ_deliveryOrder + templ_deliveryOrderItem + templ_handlingUnit
            $(this.el).find('.dimension_tree').html('').append($(treeHtml));
        }else{
            $(this.el).find('.dimension_tree').html('').append($(templ_dim));
            $(this.el).find('.measure_tree').html('').append($(templ_measure));
        }

        Saiku.i18n.translate();
    },

    select: function(event) {
        var $target = $(event.target).hasClass('root')
            ? $(event.target) : $(event.target).parent().find('span');
        if ($target.hasClass('root')) {
            $target.find('a').toggleClass('folder_collapsed').toggleClass('folder_expand');
            $target.toggleClass('collapsed').toggleClass('expand');
            $target.parents('li').find('ul').children('li').toggle();
        }

        return false;
    },

    select_dimension: function(event) {
        var $target = $(event.target);
        var checked = $target.is(':checked');
        $target.parent().find('input').prop('checked', checked);
    },

    select_all_dimensions: function(event) {
        var $target = $(event.target);
        var checked = $target.is(':checked');
        $(this.el).find('.dimension_tree input').prop('checked', checked);
    },

    select_all_measures: function(event) {
        var $target = $(event.target);
        var checked = $target.is(':checked');
        $(this.el).find('.measure_tree input').prop('checked', checked);
    },

    select_measure: function(event) {
        var $target = $(event.target);
        var checked = $target.is(':checked');
        if(checked) {
            //$target.parent().siblings().find('input').attr('checked', false);
        }
    },


    ok: function() {
        // Notify user that updates are in progress
        var $loading = $("<div>Drilling through...</div>");
        $(this.el).find('.dialog_body').children().hide();
        $(this.el).find('.dialog_body').prepend($loading);
        var selections = "";
        $(this.el).find('.check_level:checked').each( function(index) {
            if (index > 0) {
                selections += ", ";
            }
            var selection=$(this).val();
            var $caption=$(this).next('.do_field_caption');
            if($caption.length>0){
                var caption=$caption.text().trim();
                var field=selection.substring(selection.indexOf('.')+1,selection.length);
                if(caption && caption!=field){
                	selection+='#'+caption;
                }
            }
            selections += selection;
        });

        var maxrows = $(this.el).find('.maxrows').val();
        var params = "?maxrows=" + maxrows;
        params = params + (typeof this.position !== "undefined" ? "&position=" + this.position : "" );
        params += "&returns=" + selections;
        if (this.action == "export") {
            var location = Settings.REST_URL +
                Saiku.session.username + "/query/" +
                this.query.id + "/drillthrough/export/csv" + params;
            this.close();
            window.open(location);
        } else if (this.action == "table") {
            Saiku.ui.block("Executing drillthrough...");
            this.query.action.get("/drillthrough", { data: { position: this.position, maxrows: maxrows , returns: selections}, success: this.drilled } );
            this.close();
        }

        return false;
    },

    drilled: function(model, response) {
        var html = "";
        if (response != null && response.error != null) {
            html = safe_tags_replace(response.error);
        } else {
            var tr = new SaikuTableRenderer();
            html = tr.render(response);
        }

        //table.render({ data: response }, true);
        
        if(typeof html === 'undefined'){
        	html = 'No Results';
        }

        Saiku.ui.unblock();
        var html = '<div id="fancy_results" class="workspace_results" style="overflow:visible">' + html + '</div>';
        this.remove();
        $.fancybox(html
            ,
            {
                'autoDimensions'    : false,
                'autoScale'         : false,
                'height'            :  ($("body").height() - 100),
                'width'             :  ($("body").width() - 100),
                'transitionIn'      : 'none',
                'transitionOut'     : 'none'
            }
        );

    },

    finished: function() {
        $(this.el).dialog('destroy').remove();
        this.query.run();
    }
});
