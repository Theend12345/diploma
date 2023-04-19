  var obj1;
  var kmo;
  var v3;
  var lim;
  var station;

  var xhttp = new XMLHttpRequest();
  var xhttpe2 = new XMLHttpRequest();

    function graph2(mas){

        google.load("visualization", "1", {packages:["corechart"]});
        google.setOnLoadCallback(drawChart);
        function drawChart() {
         var data = google.visualization.arrayToDataTable(mas);
         var options = {
          title: 'Статистика появления неисправностей',
          hAxis: {title: 'Даты неисправностей'},
          vAxis: {title: 'Количество неисправностей'}
         };
         var chart = new google.visualization.ColumnChart(document.getElementById('oil'));
         chart.draw(data, options);
        }
    }



    function graph3(mas){

        google.load("visualization", "1", {packages:["corechart"]});
        google.setOnLoadCallback(drawChart);
        function drawChart() {
         var data = google.visualization.arrayToDataTable(mas);
         var options = {
          title: 'Статистика ограничений движения',
          hAxis: {title: 'Ограничения'},
          vAxis: {title: 'Количество неисправностей'}
         };
         var chart = new google.visualization.ColumnChart(document.getElementById('lim'));
         chart.draw(data, options);
        }
    }

    function graph4(mas){

        google.load("visualization", "1", {packages:["corechart"]});
        google.setOnLoadCallback(drawChart);
        function drawChart() {
         var data = google.visualization.arrayToDataTable(mas);
         var options = {
          title: 'Статистика появления неисправностей',
          hAxis: {title: 'Неисправности'},
          vAxis: {title: 'Количество неисправностей'}
         };
         var chart = new google.visualization.ColumnChart(document.getElementById('deff'));
         chart.draw(data, options);
        }
    }


    function graph(values){
        
        google.load("visualization", "1", {packages:["corechart"]});
        google.setOnLoadCallback(drawChart);
        function drawChart() {
         var data = google.visualization.arrayToDataTable([
          ['Статус', 'Объём'],
          ['Выполнено', Number.parseFloat(values[0].replace(",",".")) ],
          ['В работе', Number.parseFloat(values[1].replace(",","."))],
          ['Ожидает подтверждения',  Number.parseFloat(values[2].replace(",","."))],
         ]);
         var options = {
            title: 'Анализ работы над неисправностями',
          is3D: true,
         };
         var chart = new google.visualization.PieChart(document.getElementById('air'));
          chart.draw(data, options);
        }

    }


    function findByDateBetween() {

        var v1 = document.getElementById("v1").value;
        var v2 = document.getElementById("v2").value;

        loadTable();

        if(v2==""){
            v2 = v1;
        }
        if(obj1 == null){
            xhttp.open("GET", "http://localhost:8080/act2/findByDateBetween?v1=" + v1 + "&v2=" + v2, true);
            console.log("findByDateBetween");
        }

        else if(obj1 != null && v3 != null){
            xhttp.open("GET", "http://localhost:8080/act2/findByObjectAndDefectAndDateBetween?obj=" + obj1 + "&v3=" + v3+ "&v1=" + v1+ "&v2=" + v2, true);
            console.log("findByObjectAndDefectAndDateBetween");
            console.log(obj1);
        }

        else{
            xhttp.open("GET", "http://localhost:8080/act2/findByObjectAndDateBetween?obj=" + obj1 + "&v1=" + v1+ "&v2=" + v2, true);
            console.log("findByObjectAndDateBetween");
        }
        xhttp.send();
    }

    function loadAct() {
        loadTable();
        xhttp.open("GET", "http://localhost:8080/act2/findAll", true);
        xhttp.send();
    }

    function findByObject(obj) {
        var value = obj.value;
        obj1 = obj.value;
        
        loadTable();

        xhttp.open("GET", "http://localhost:8080/act2/findByObject?obj=" + value, true);
        

        if(value === ""){
            xhttp.open("GET", "http://localhost:8080/act2/findAll", true);
        }
        xhttp.send();

        getDefects(value);
    }

    function searchByName(){
        var name = document.getElementById("search_field").value;

        loadTableStation()
        
        xhttpe2.open("GET", "http://localhost:8080/act2/findByName?name="+name, true);
        xhttpe2.send();
        Location.reload()
    }

    function findByKmo(id_kmo) {
        var value = id_kmo.value;
        kmo = id_kmo.value;
        
        loadTable();

        xhttp.open("GET", "http://localhost:8080/act2/findByKmo?id_kmo=" + value, true);
        

        if(value === ""){
            xhttp.open("GET", "http://localhost:8080/act2/findAll", true);
        }
        xhttp.send();

    }


    function findByStation(st) {
        var value = st.value;
        station = st.value;
        
        loadTable();

        xhttp.open("GET", "http://localhost:8080/act2/findByStation?st=" + value, true);
        

        if(value === ""){
            xhttp.open("GET", "http://localhost:8080/act2/findAll", true);
        }
        xhttp.send();
    }


    function findByLimit(limit) {
        var value = limit.value;
        lim = limit.value;
        
        loadTable();

        xhttp.open("GET", "http://localhost:8080/act2/findByLimit?lim=" + value, true);
        

        if(value === ""){
            xhttp.open("GET", "http://localhost:8080/act2/findAll", true);
        }
        xhttp.send();

    }


    function findByDefect(def) {
        v3 = def.value;

        loadTable();

        xhttp.open("GET", "http://localhost:8080/act2/findByDefect?def=" + v3, true);
        xhttp.send();
    }


    function getDefects(value1) {
        
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {

            if (this.readyState == 4 && this.status == 200) {
                var array = JSON.parse(this.responseText);
                array.sort();

                    var data = {
                      Id: array
                    };

                    if($('#list').empty() == false){
                        $('#list').clear();
                    }

                    $('#list').append(
                      data.Id.map(function(v) {
                        return $('<option/>', {
                          value: v,
                          text: v
                        })
                      })
                    ).change(function() {

                    });
                
            }
        };

        xhttp.open("GET", "http://localhost:8080/act2/getDefects?sheet=" + value1, true);
        xhttp.send();

    }

    function getFrequentFailure(acts) {
        var xhttpe = new XMLHttpRequest();
        var def = ["",""];
        for(var i = 0; i< acts.length; i++){
            def[i] = acts[i].defect;
        }

        xhttpe.onreadystatechange = function () {

            if (xhttpe.readyState == 4 && xhttpe.status == 200) {
            document.getElementById("most").innerHTML  = "Самая частая неисправность: " + JSON.parse(JSON.stringify(xhttpe.responseText));

        }
        
        }
      
        
        xhttpe.open("GET", "http://localhost:8080/act2/getFrequentFailure?data=" + def, true);
        
        xhttpe.send();
               

    }



    function getDataStatus(datas) {
        var xhttpe = new XMLHttpRequest();
        var values;
        var def = ["",""];
        for(var i = 0; i< datas.length; i++){
            def[i] = datas[i].status;
        }


        xhttpe.onreadystatechange = function () {
            
            if (xhttpe.readyState == 4 && xhttpe.status == 200) {
                values = JSON.parse(xhttpe.responseText);
                console.log(values);
                graph(values)

        }
                
        }
        
        xhttpe.open("POST", "http://localhost:8080/act2/getDataStatus?data=" + def, true);
        xhttpe.send();

    }

    function getDataDefects(datas) {
        var xhttpe = new XMLHttpRequest();
        var values;
        var def = ["",""];
        for(var i = 0; i< datas.length; i++){
            def[i] = datas[i].date;
        }


        xhttpe.onreadystatechange = function () {
            
            if (xhttpe.readyState == 4 && xhttpe.status == 200) {
                values = JSON.parse(xhttpe.responseText);
                var mas = [['Дата','Количество неисправностей']];
                var i = 1;
                var m = 5
                for ([key, value] of Object.entries(values)) {

                    mas[i] = []
                    
                                mas[i][0] = key;
                                mas[i][1] = value;
                                i++;

                  }
                  console.log(mas);
                  graph2(mas)

        }
                
        }
        
        xhttpe.open("POST", "http://localhost:8080/act2/getDataDefects?data=" + def, true);
        xhttpe.send();

    }


    function getDataLimits(datas) {
        var xhttpe = new XMLHttpRequest();
        var values;
        var def = ["",""];
        for(var i = 0; i< datas.length; i++){
            def[i] = datas[i].limit;
        }


        xhttpe.onreadystatechange = function () {
            
            if (xhttpe.readyState == 4 && xhttpe.status == 200) {
                values = JSON.parse(xhttpe.responseText);
                var mas = [['Ограничение','Количество']];
                var i = 1;
                var m = 5
                for ([key, value] of Object.entries(values)) {

                    mas[i] = []
                    
                                mas[i][0] = key;
                                mas[i][1] = value;
                                i++;

                  }
                  console.log(mas);
                  graph3(mas)

        }
                
        }
        
        xhttpe.open("POST", "http://localhost:8080/act2/getDataDefects?data=" + def, true);
        xhttpe.send();

    }


    function getDataDefect(datas) {
        var xhttpe = new XMLHttpRequest();
        var values;
        var def = ["",""];
        for(var i = 0; i< datas.length; i++){
            def[i] = datas[i].defect;
        }


        xhttpe.onreadystatechange = function () {
            
            if (xhttpe.readyState == 4 && xhttpe.status == 200) {
                values = JSON.parse(xhttpe.responseText);
                var mas = [['Неисправности','Количество']];
                var i = 1;
                var m = 5
                for ([key, value] of Object.entries(values)) {

                    mas[i] = []
                    
                                mas[i][0] = key;
                                mas[i][1] = value;
                                i++;

                  }
                  console.log(mas);
                  graph4(mas)

        }
                
        }
        
        xhttpe.open("POST", "http://localhost:8080/act2/getDataDefects?data=" + def, true);
        xhttpe.send();

    }



    function loadTable(){
       
                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        var acts = JSON.parse(this.responseText);
                        var html = '<tr>\n' +
                        
                            '        <th>Ограничение</th>\n' +
                            '        <th>Комментарий</th>\n' +
                            '        <th>Дата назначения</th>\n' +
                            '        <th>Неисправность</th>\n' +
                            '        <th>Станция</th>\n' +
                            '        <th>КМО</th>\n' +
                            '        <th>Объект</th>\n' +
                            '        <th>Дата выполнения</th>\n' +
                            
                            '        <th>Значение</th>\n' +
                            '    </tr>';
                        for (var i = 0; i < acts.length; i++) {
                            var act = acts[i];
                            if(act.resolve_date == null){
                                act.resolve_date = "";
                            }
                            html = html + '<tr><td>' + act.limit + '</td>\n' +
                                
                                '        <td>' + act.commit + '</td>\n' +
                                '        <td style = "text-align: center;">' + act.date + '</td>' +
                                '        <td>' + act.defect + '</td>' +
                                '        <td>' + act.station + '</td>' +
                                '        <td style = "text-align: center;">' + act.kmo + '</td>' +
                                '        <td>' + act.object + '</td>' +
                                '        <td style = "text-align: center;">' + act.resolve_date + '</td>' +
                             
                                '        <td>' + act.value+ '</td>';


                        }

                        document.getElementById("actList").innerHTML = html;
                        getFrequentFailure(acts)
                        getDataStatus(acts)
                        getDataDefects(acts)
                        getDataLimits(acts)
                        getDataDefect(acts)
                        changeRowColor(acts)
                        
                    }

                };
    }

    var sts;
    
    function loadTableStation(){
        
        xhttpe2.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                var stations = JSON.parse(this.responseText);
                sts = stations;
                var html = '<tr>\n' +
                    '        <th>Название</th>\n' +
                    '        <th>ESR</th>\n' +
                    '        <th>Изменить</th>\n' +
                    '    </tr>';
                for (var i = 0; i < stations.length; i++) {
                    var station = stations[i];
                    
                    html = html + '<tr><td>' + station.name + '</td>\n' +
                        
                         '  <td>' + station.esr + '</td>\n' +
                         '  <td style = "text-align: center;"><a href="index.html#updt"><button onclick="getIdToUpdate('+station.id+')" class="button-28" role="button">Изменить</button></a></td>\n' +
                         '  </td>';
                         
                    }
                document.getElementById("stList").innerHTML = html;     
            }
        };
        
     }

     


     function loadStTable(){

        loadTableStation()
        xhttpe2.open("GET", "http://localhost:8080/act2/findAllStation", true);
        xhttpe2.send();
        

     }

    var stationID;
    
     function getIdToUpdate(id){
        stationID = id;
        var stName = sts[id-1].name;
        document.getElementById("statinf").innerHTML  = stName;
     }


     function updateStation(){
        var name = document.getElementById("update_field").value;

        var xhttpe = new XMLHttpRequest();
        xhttpe.open("GET", "http://localhost:8080/act2/updateStation?id="+ stationID + "&name=" + name, true);
        xhttpe.send();
        loadStTable()
        location.reload();
     }


     function generatePDF() {
        const element = document.getElementById('invoice');

        html2pdf(element, {
            margin:       10,
            filename:     'myfile.pdf',
            image:        { type: 'jpeg', quality: 0.98 },
            html2canvas:  { scale: 2, logging: true, dpi: 192, letterRendering: true },
            jsPDF:        { unit: 'mm', format: 'a3', orientation: 'portrait' }
          });

        
    }


    function changeRowColor(acts) {

        var elem = document.getElementsByTagName('table');

        for (var i = 0; i < elem.length; i++) {

            for (var j = 0; j < elem[i].getElementsByTagName('td').length; j++) {
                
                        console.log(acts[j].status);
                    if(acts[j].status===1){
                        document.getElementsByTagName('tr')[j+1].style.backgroundColor = 'rgba(71,186,232,0.91)';
                    }
                    else if(acts[j].status===-1){
                    document.getElementsByTagName('tr')[j+1].style.backgroundColor = 'rgba(232,117,71,0.91)';
                    }

                    else{
                        document.getElementsByTagName('tr')[j+1].style.backgroundColor = 'white';
                    }

            }

        }
    }


    loadAct();
    loadStTable();
    