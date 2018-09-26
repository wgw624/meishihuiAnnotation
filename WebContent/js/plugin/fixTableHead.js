/**
 * Created by wei_guangwei on 2017/11/22.
 */
(function($, window, undefined) {
    var elems = $([]),
        jq_resize = $.resize = $.extend($.resize, {}),
        timeout_id,
        str_setTimeout = 'setTimeout',
        str_resize = 'resize',
        str_data = str_resize + '-special-event',
        str_delay = 'delay',
        str_throttle = 'throttleWindow';
    jq_resize[str_delay] = 200;
    jq_resize[str_throttle] = true;
    $.event.special[str_resize] = {
        setup: function() {
            if (!jq_resize[str_throttle] && this[str_setTimeout]) {
                return false;
            }
            var elem = $(this);
            elems = elems.add(elem);
            $.data(this, str_data, {
                w: elem.width(),
                h: elem.height()
            });
            if (elems.length === 1) {
                loopy();
            }
        },
        teardown: function() {
            if (!jq_resize[str_throttle] && this[str_setTimeout]) {
                return false;
            }
            var elem = $(this);
            elems = elems.not(elem);
            elem.removeData(str_data);
            if (!elems.length) {
                clearTimeout(timeout_id);
            }
        },
        add: function(handleObj) {
            if (!jq_resize[str_throttle] && this[str_setTimeout]) {
                return false;
            }
            var old_handler;
            function new_handler(e, w, h) {
                var elem = $(this),
                    data = $.data(this, str_data);
                data.w = w !== undefined ? w : elem.width();
                data.h = h !== undefined ? h : elem.height();
                old_handler.apply(this, arguments);
            }
            if ($.isFunction(handleObj)) {
                old_handler = handleObj;
                return new_handler;
            } else {
                old_handler = handleObj.handler;
                handleObj.handler = new_handler;
            }
        }
    };

    function loopy() {
        timeout_id = window[str_setTimeout](function() {
            elems.each(function() {
                var elem = $(this),
                    width = elem.width(),
                    height = elem.height(),
                    data = $.data(this, str_data);
                if (width !== data.w || height !== data.h) {
                    elem.trigger(str_resize, [data.w = width, data.h = height]);
                }
            });
            loopy();
        }, jq_resize[str_delay]);
    }
})(jQuery, this);

/**
 * 固定表头
 */
;(function($,window,document,undefined){
    var fixTableHead = function(table){
        this.$element = table,
        this.options={
                'bgcolor':'rgb(255,255,255)',
                'tabHeadBgcolor':'rgb(255,255,255)',
                'colPos':'absolute',
                'w':'100%',
                'h':'100%',
                'fixRowId':'fixHeadId',
                'fixColId':'fixColId',
                'fixBodyId':'fixBodyId',
                'calMaxTdDivId':'calMaxWithTd',
                'fixTableDataId':'fixTableDataId',
        }
    };
    fixTableHead.prototype = {
        fixRow:function(){
            var fixTableH = this;
            this.$element.each(function(index,dom){
                fixTableH.deleteFixTableHead(index,dom);
                var fixHeadObj = fixTableH.getFixHeadObj(index,dom,fixTableH);
                var tableWidth = $(dom).width();
                var tablePar = $(dom).parent();
                if($(dom).find("td[class='fixCol'],th[class='fixCol']").length>0){
                    var fixTabHeadHtml = fixTableH.getFixTabHeadHtml(index,dom,fixTableH,fixHeadObj,tableWidth);
                }else{
                    var fixTabHeadHtml = fixTableH.getFixTabHeadHtml(index,dom,fixTableH,fixHeadObj);
                }
                var headTabbleDiv = fixTableH.createTabHead(index,dom,fixTableH,fixTabHeadHtml,fixHeadObj.fixH);

                if($(dom).attr("id") == undefined){
                    $(dom).attr("id",fixTableH.options.fixTableDataId+index);
                }

                if($(dom).find("td[class='fixCol'],th[class='fixCol']").length>0){
                    var fixTabColW = fixTableH.getColHeadW(dom);
                    var dataOption={
                        parHeight:$(tablePar).height(),
                        parWidth:$(tablePar).width(),
                        fixTableHeight:fixHeadObj.fixH,
                        fixTableWidth:fixTabColW,
                        tableWidth:$(dom).width(),
                    }

                    var bodyTableDiv = fixTableH.createTabBody(index,dom,fixTableH,dataOption);
                    var fixColDiv = fixTableH.createFixColTable(index,dom,fixTableH,fixTabColW);
                    var fixColHeadDiv = fixTableH.createFixColHead(index,dom,fixTableH,fixTabHeadHtml,dataOption);

                    /*var htm = fixColHeadDiv[0].outerHTML + headTabbleDiv[0].outerHTML + fixColDiv[0].outerHTML  + bodyTableDiv[0].outerHTML*/
                    var htm =  headTabbleDiv[0].outerHTML + fixColDiv[0].outerHTML +fixColHeadDiv[0].outerHTML  + bodyTableDiv[0].outerHTML
                }else{
                    var bodyTableDiv = fixTableH.createTabBody(index,dom,fixTableH);
                    var htm = headTabbleDiv[0].outerHTML + bodyTableDiv[0].outerHTML
                }

                $(tablePar[0]).html(htm);
                $(tablePar[0]).css("overflow","hidden");
                $(tablePar[0]).css("position","relative");
                fixTableH.bindScroll(fixTableH.options.fixBodyId+index,fixTableH.options.fixRowId+index,fixTableH.options.fixColId+index);
                var tableId = $(dom).attr('id');

                $("#"+tableId).resize(function(){
                    var headId = fixTableH.options.fixRowId+index;
                    var tableW = $(this).width();
                    $("#"+headId).css({'width':tableW});
                });
            });

        },
        /**
         *获取固定列 div 容器宽度
         */
        getColHeadW:function(dom){
            var trObj = $(dom).find("tr").eq(0);
            var divW = 0;
            $(trObj).find("td[class=fixCol],th[class=fixCol]").each(function(){
                divW = divW + $(this).outerWidth();
            });
            return divW;
        },

        /**
         *获得固定 列的 表头div 容器
         */
        createFixColHead:function(index,dom,fixTableH,fixColHeadHtml,dataOption){
            var fixColHeadDiv = $("<div></div>")
            fixColHeadDiv.css({'width':dataOption.fixTableWidth,'height':dataOption.fixTableHeight,'position':'absolute','background-color':fixTableH.options.tabHeadBgcolor,'overflow':'hidden'});
            fixColHeadDiv.html(fixColHeadHtml)
            return fixColHeadDiv;
        },
        /**
         *获得固定 列的div 容器
         */
        createFixColTable:function(index,dom,fixTableH,fixTabColW){
            var fixColHtml = "";
            var fixColTabDiv = $("<div class='fixTableColDiv'></div>")
            fixColTabDiv.attr("id",fixTableH.options.fixColId+index);
            fixColTabDiv.css({'width':fixTabColW,'background-color':fixTableH.options.tabHeadBgcolor,'position':'absolute'});

            $(dom).find("tr").each(function(index){
                var tdHtml = "";
                var tr = $("<tr></tr>");
                $(this).find("th[class=fixCol],td[class=fixCol]").each(function(index){
                    tdHtml = tdHtml +$(this)[0].outerHTML;
                });
                $(tr).html(tdHtml);
                fixColHtml = fixColHtml + $(tr)[0].outerHTML;
            });
            if($(dom).attr("cellspacing") == 0 && $(dom).attr("cellpadding") == 0){
                var table = $("<table class='fixTableCol fixTableHeadTable' cellpadding='0' cellspacing='0'>"+fixColHtml+"</table>")
            }else{
                var table = $("<table class='fixTableCol fixTableHeadTable' >"+fixColHtml+"</table>")
            }
            fixColTabDiv.html(table);
            return fixColTabDiv;
        },
        /**
         *获得固定表头的表头对象，返回值包括固定的表头的高度和 tr 的html 字符串
         */
        getFixHeadObj:function(index,dom,fixTableH){
            var fixTr = $(dom).find("tr[class=fixRow]");
            var fixHtml = "";
            var fixH = 0;
            if(fixTr !=undefined && fixTr.length>0){
                $(fixTr).each(function(){
                    fixHtml = fixHtml + $(this)[0].outerHTML;
                    fixH = fixH+$(this).outerHeight();
                });
            }else{
                var th = $(dom).find("tr th");
                fixH = th.eq(0).outerHeight();
                var trh = $(dom).find("tr").eq(0);
                fixHtml = trh[0].outerHTML
            }
            return {'fixH':fixH,'fixHtml':fixHtml};
        },
        /**
         * 获得固定表头的表头table html 字符串
         */
        getFixTabHeadHtml:function(index,dom,fixTableH,fixHeadObj,tableWidth){

            var fixH = fixHeadObj.fixH;
            var fixHtml = fixHeadObj.fixHtml;
            var tablecls = $(dom).attr("class");
            var maxWidthTr = fixTableH.getMaxWidthTr(index,dom,fixTableH);
           /* var tableHead = "<table  class = '"+tablecls+ " uu' >"+ fixHtml + maxWidthTr[0].outerHTML+"</table>"*/
           if($(dom).attr("cellspacing") == 0 && $(dom).attr("cellpadding") == 0){
               var tableHead = $("<table  cellpadding='0' cellspacing='0'></table>");
           }else{
               var tableHead = $("<table></table>");
           }
            tableHead.addClass(tablecls);
            tableHead.addClass("tableHeadStyle");
            if(tableWidth){
                tableHead.css({width:tableWidth});
            }
            tableHead.html(fixHtml + maxWidthTr[0].outerHTML);

            return tableHead;
        },

        /**
         * 获得固定表头的 表头div 容器 html字符串
         */
        createTabHead:function(index,dom,fixTableH,tableHead,fixHeadHeight){

            var headId = fixTableH.options.fixRowId+index;
            var fTabHeadDiv = $("<div></div>")
            fTabHeadDiv.css({'width':$(dom).width(),'height':fixHeadHeight,'background-color':fixTableH.options.tabHeadBgcolor,'overflow':'hidden','position':'absolute'})
            fTabHeadDiv.attr("id",headId)
            fTabHeadDiv.addClass('fixTableHead')
            $(fTabHeadDiv[0]).html(tableHead[0].outerHTML);
            return fTabHeadDiv;
        },
        /**
         * 获得固定表头数据显示区 的div 容器
         */
        createTabBody:function(index,dom,fixTableH,dataOption){
            var fixBodyId = fixTableH.options.fixBodyId+index;
            var fTabDiv = $("<div class='fixTableBody'></div>");
            $(fTabDiv).attr("id",fixBodyId);
            if(dataOption){
                var h = dataOption.parHeight - dataOption.fixTableHeight;
                var w = dataOption.parWidth - dataOption.fixTableWidth;
                fTabDiv.css({'width':w,height:h,'overflow':'auto','position':'absolute','margin-top':dataOption.fixTableHeight,'margin-left':dataOption.fixTableWidth});
                $(dom).css({'margin-left':-dataOption.fixTableWidth,'margin-top':-dataOption.fixTableHeight,width:dataOption.tableWidth})

            }else{
                fTabDiv.css({'width':fixTableH.options.w,height:fixTableH.options.h,'overflow':'auto'});
            }

            $(fTabDiv).html($(dom)[0].outerHTML)
            $(dom).width($(dom).width());
            return fTabDiv;
        },
        deleteFixTableHead:function(index,dom){
            var par = $(dom).parent();
            if($(par).hasClass("fixTableHead,fixTableColDiv")){
                var tableObj = dom;
                var tabPar = $(dom).parent().parent();
                $(tabPar).html(tableObj);
            }
        },
        getMaxWidthTr:function(index,dom,fixTableObj){

            var trNum = 0;
            var isFlag = false;
            var tdDiv = $("<div style='display:none'></div>");
            tdDiv.attr("id",fixTableObj.options.calMaxTdDivId)
            $("body").append(tdDiv);

            if($("#"+fixTableObj.options.calMaxTdDivId).length>0){
                isFlag =true;
            }
            var rowCount = $(dom).find("tr").length;

            var colCount = $($(dom).find("tr").eq(rowCount-1)).find("td").length;
            var TdText = "";
            for(var i=0;i<colCount;i++){
                var tdW = 0;
                var tdWB = -1;
                var maxTdText = "";
                for(var j =1;j<rowCount;j++){
                    var trObj = $(dom).find("tr").eq(j)
                    if($(trObj).find("td,th").length<1){
                        continue;
                    }
                    var tdObj = $(trObj).find("td,th").eq(i);

                    var tdText = $(tdObj).html();
                    if(isFlag){
                        $("#calMaxWithTd").text(tdText)
                        tdW = $("#calMaxWithTd").width();
                        if(tdW > tdWB){
                            tdWB = tdW;
                            maxTdText = $(tdObj)[0].outerHTML;
                        }
                        $("#calMaxWithTd").text("");
                    }else{
                        if(tdText.length > tdW){
                            tdW = tdText.length;
                            maxTdText = $(tdObj)[0].outerHTML;
                        }
                    }
                }
                TdText = TdText + maxTdText;
            }
            var headTrObj = $("<tr>"+TdText+"</tr>")
            $("#"+fixTableObj.options.calMaxTdDivId).remove();
            return headTrObj;
        },
        bindScroll:function(scrollId,bindRowId,bindColId){
            var scrollLeft = 0;
            var scrollTop = 0;
            $("#"+scrollId).scroll(function(){
                var left = this.scrollLeft;
                var top = this.scrollTop;
                if(scrollLeft !=left){
                    $("#"+bindRowId).css("left",-left);
                }
                if(scrollTop!=top){
                    $("#"+bindColId).css("top",-top);
                }
                scrollLeft = left;
                scrollTop = top;
            });
        },
    };

    $.fn.fixTableHeadRow = function(){
        var thr = new fixTableHead(this);
        return thr.fixRow();
    }

})(jQuery,window,document);


