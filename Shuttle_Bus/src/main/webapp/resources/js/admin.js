$(document).ready(function () {
    $('ul.tabs').tabs();
    $('.modal').modal();
    $(".button-collapse").sideNav();
    $('#modal2').modal({
        dismissible: false
    });
    $('select').material_select();
    $('#modal3').modal();
    $('#view_schedule').modal();
    $('#edit_bus_list').modal();
    $('#modal_reason').modal();
    $('#modal_notif').modal({
        dismissible: false
    });
    $('#delete_bus_list').modal();
    $('#detail_c_pass').modal();

    $('#leaveDate').flatpickr({
        mode: "single",
        minDate: "today",
        dateFormat: "Y-m-d"
    });
    $('#returnDate').flatpickr({
        mode: "single",
        minDate: "today",
        dateFormat: "Y-m-d"
    });
    $('#deadlineBooking').flatpickr({
        mode: "single",
        minDate: "today",
        dateFormat: "Y-m-d h:i K",
        enableTime: true
    });


    $('.dropdown-button').dropdown({
            inDuration: 300,
            outDuration: 225,
            constrainWidth: false, // Does not change width of dropdown to that of the activator
            hover: false, // Activate on hover
            gutter: 0, // Spacing from edge
            belowOrigin: true, // Displays dropdown below the button
            alignment: 'right', // Displays dropdown with edge aligned to the left of button
            stopPropagation: false // Stops event propagation
        }
    );


    var data_schedule = [];
    $.ajax({
        async: false,
        cache: false,
        type: "GET",
        contentType: "application/json",
        url: "show_schedule",


        timeout: 100000,
        success: function (data) {
            data_schedule = data;
            var tr = '';
            var td = '';
            var view = '';
            var set = '';
            for (var i = 0; i < data.length; i++) {
                if (data[i]["student"] == undefined) {
                    data[i]["student"] = 0;
                }
                if (data[i]["staff"] == undefined) {
                    data[i]["staff"] = 0;
                }
                if (data[i]["customer"] == undefined) {
                    data[i]["customer"] = 0;
                }
                if (data[i]["status"]) {
                    set = '<a class="modal-trigger view_pass_booked" value="' + data[i]["date"] + "->" + data[i]["destination_id"] +
                        '" href="#view_schedule">View</a>/<a class="modal-trigger edit_schedule_modal" value="' + data[i]["date"] + "->" +
                        data[i]["destination_id"] + "->" + i + '" href="#edit_schedule">Edit</a>';
                } else {
                    set = '<a class="modal-trigger" href="#modal2">Set</a>';
                }
                td = '<td>' + data[i]["date"] + '</td>' +
                    '<td>' + data[i]["destination"] + '</td>' +
                    '<td>' + data[i]["student"] + '</td>' +
                    '<td>' + data[i]["staff"] + '</td>' +
                    '<td>' + data[i]["customer"] + '</td>' +
                    '<td class="detail" value="' + i + '"> <a class="modal-trigger" href="#modal1">Detail</a></td>' +
                    '<td class="set_schedule" value="' + i + '">' + set + '</td>';

                tr = tr + '<tr>' + td + '</tr>';


            }
            document.getElementById('getschedule').innerHTML = tr;
        },
        error: function (e) {
            console.log("ERROR: ", e);

        },
        done: function (e) {
            console.log("DONE");
        }
    });
    $(".detail").click(function () {

        var tr = '<tr>';
        var td = '';


        var arr = $(this).attr('value');

        var i = parseInt(arr);


        for (var j = 0; j < data_schedule[i]["list"].length; j++) {
            td = '<td>' + data_schedule[i]["list"][j]["name"] + '</td>' +
                '<td>' + data_schedule[i]["list"][j]["batch"] + '</td>' +
                '<td>' + data_schedule[i]["list"][j]["role"] + '</td>' +
                '<td>' + data_schedule[i]["list"][j]["email"] + '</td>' +
                '<td>' + data_schedule[i]["list"][j]["phone"] + '</td>';

            tr = tr + td + '</tr>';

        }
        for (var j = 0; j < data_schedule[i]["list_cus"].length; j++) {
            td = '<td>' + data_schedule[i]["list_cus"][j]["name"] + '</td>' +
                '<td>' + data_schedule[i]["list_cus"][j]["batch"] + '</td>' +
                '<td>' + data_schedule[i]["list_cus"][j]["role"] + '</td>' +
                '<td>' + data_schedule[i]["list_cus"][j]["email"] + '</td>' +
                '<td>' + data_schedule[i]["list_cus"][j]["phone"] + '</td>';
            tr = tr + td + '</tr>';
        }


        document.getElementById('detail_title').innerHTML = 'User Detail on ' + arr[0];
        document.getElementById('detail_pass').innerHTML = tr;

    });

    $("a.view_pass_booked").click(function () {
        var param = $(this).attr('value').split('->');
        var date_dest = {
            "date_of_travel": param[0],
            "destination_id": param[1]
        };

        $.ajax({
            async: false,
            cache: false,
            contentType: 'application/json; charset=utf-8',
            type: "POST",
            data: JSON.stringify(date_dest),
            url: "detail_pass_schedule",
            timeout: 100000,
            success: function (data) {
                var tr = '';
                var td = '';
                for (var i = 0; i < data.length; i++) {
                    td = '<td>' + data[i]["driver"] + '</td>' +
                        '<td>' + data[i]["bus"] + '</td>' +
                        '<td>' + data[i]["remaining"] + '</td>' +
                        '<td>' + data[i]["total_seats"] + '</td>' +
                        '<td>' + data[i]["departure"] + '</td>' +
                        '<td>' + data[i]["arrival"] + '</td>' +
                        '<td>' + data[i]["customer_only"] + '</td>' +
                        '<td id="detail_book" value="' + i + '">' + '<a class="modal-trigger" href="#detail_c_pass">view</a>' + '</td>';
                    tr = tr + '<tr>' + td + '</tr>';
                }

                document.getElementById('view_pass').innerHTML = tr;
                $("#detail_book").click(function () {
                    var index = parseInt($(this).attr('value'));
                    console.log(index);
                    var td = '';
                    var tr = '';
                    for (var i = 0; i < data[index]["detail"].length; i++) {
                        td = '<td>' + data[index]["detail"][i]["name"] + '</td>' +
                            '<td>' + data[index]["detail"][i]["role"] + '</td>' +
                            '<td>' + data[index]["detail"][i]["batch"] + '</td>' +
                            '<td>' + data[index]["detail"][i]["email"] + '</td>' +
                            '<td>' + data[index]["detail"][i]["phone"] + '</td>';
                        tr = tr + '<tr>' + td + '</tr>';
                    }
                    document.getElementById('view_c_pass').innerHTML = tr;
                });

            },
            error: function (e) {
                console.log("ERROR: ", e);

            },
            done: function (e) {
                console.log("DONE");
            }
        });
    });
    var driver_list = [];
    var bus_list = [];
    $("a.edit_schedule_modal").click(function () {
        count_schedule_element = [];
        var param = $(this).attr('value').split('->');
        console.log(param[2]);
        index_of = param[2];
        var date_dest = {
            "date_of_travel": param[0],
            "destination_id": param[1]
        };

        if (driver_list.length == 0 && bus_list.length == 0) {
            $.ajax({
                async: false,
                cache: false,
                type: "GET",
                contentType: "application/json",
                url: "driver_list",
                timeout: 100000,
                success: function (data) {
                    driver_list = data;
                },
                error: function (e) {
                    console.log("ERROR: ", e);

                },
                done: function (e) {
                    console.log("DONE");
                }
            });

            $.ajax({
                async: false,
                cache: false,
                type: "GET",
                contentType: "application/json",
                url: "schedule_bus",
                timeout: 100000,
                success: function (data) {
                    bus_list = data;
                },
                error: function (e) {
                    console.log("ERROR: ", e);

                },
                done: function (e) {
                    console.log("DONE");
                }
            });

        }

        $.ajax({
            async: false,
            cache: false,
            contentType: 'application/json; charset=utf-8',
            type: "POST",
            data: JSON.stringify(date_dest),
            url: "detail_pass_schedule",
            timeout: 100000,
            success: function (data) {
                console.log(data);
                var bus_model = '';
                var driver_name = '';
                var total_seats = '';
                var departure = '';
                var arrival = '';
                var customer = '';
                var delete_schedule = '';
                var customer_only = '';
                var selected = '';
                var tr = '';
                for (var j = 0; j < data.length; j++) {
                    count_schedule = j;
                    for (var i = 0; i < bus_list.length; i++) {
                        if (data[j]["bus"] == bus_list[i]["bus_model"]) {
                            bus_model = bus_model + '<option value="' + bus_list[i]["bus_id"] + '" selected>' + bus_list[i]["bus_model"] + '</option>';
                        } else {
                            bus_model = bus_model + '<option value="' + bus_list[i]["bus_id"] + '">' + bus_list[i]["bus_model"] + '</option>';
                        }

                    }
                    for (var i = 0; i < driver_list.length; i++) {
                        if (data[j]["driver"] == driver_list[i]["driver_name"]) {
                            driver_name = driver_name + '<option value="' + driver_list[i]["user_id"] + '" selected>' + driver_list[i]["driver_name"] + '</option>';
                        } else {
                            driver_name = driver_name + '<option value="' + driver_list[i]["user_id"] + '">' + driver_list[i]["driver_name"] + '</option>';
                        }

                    }
                    for (var i = 0; i < data_schedule[param[2]]["list_cus"].length; i++) {

                        for (var m = 0; m < data[j]["detail"].length; m++) {
                            if (data[j]["detail"][m]["role"] == "customer") {
                                if (data[j]["detail"][m]["user_id"] == data_schedule[param[2]]["list_cus"][i]["user_id"]) {
                                    selected = "selected";
                                }
                            }
                        }

                        customer = customer + '<option ' + selected + ' value="' + data_schedule[param[2]]["list_cus"][i]["user_id"] + '">' +
                            data_schedule[param[2]]["list_cus"][i]["name"] + '</option>';
                         selected ='';
                    }

                    bus_model = '<td id="ebus_model' + j + '"><select>' + bus_model + '</select></td>';
                    driver_name = '<td id="edriver_name' + j + '"><select>' + driver_name + '</select></td>';
                    total_seats = '<td id="etotal_seats' + j + '"><input type="text" value="' + data[j]["total_seats"] + '" placeholder="total seats"></td>';
                    departure = '<td><div class="flatpickr departure  edata_departure' + j + '">' +
                        '<input value="' + data[j]["departure"] +
                        '" type="text" placeholder="Select Time" data-input  class=" input flatpickr-input active"></div></td>';
                    arrival = '<td><div class="flatpickr arrival edata_arrival' + j + '">' +
                        '<input value="' + data[j]["arrival"] + '" type="text" placeholder="Select Time" data-input  class="input flatpickr-input active"></div></td>';
                    customer = '<td ><select multiple class="ecustomer' + j + '"><option disabled>customer1</option>' +
                        customer + '</select></td>';
                    delete_schedule = '<td class="add_edit_icon"><i class="material-icons" value="' + j + '">delete</i></td>';
                    customer_only = '<td><input type="checkbox" id="echeck' + j + '" /><label for="echeck' + j + '"></label></td>';
                    tr = tr + '<tr class="add_input" id="etr_' + j + '">' + bus_model + driver_name + customer + total_seats + departure +
                        arrival + customer_only + delete_schedule + '</tr>';
                    count_schedule_element[count_schedule_element.length] = j;
                    customer = '';
                    driver_name = '';
                    bus_model = '';
                }


                document.getElementById('edit_more_schedule').innerHTML = tr;

                $('select').material_select();

                $(".departure input").flatpickr({
                    enableTime: true,
                    noCalendar: true,
                    enableSeconds: false,
                    time_24hr: false,
                    dateFormat: "H:i",
                    defaultHour: 12,
                    defaultMinute: 0
                });
                $(".arrival input").flatpickr({
                    enableTime: true,
                    noCalendar: true,
                    enableSeconds: false,
                    time_24hr: false,
                    dateFormat: "H:i",
                    defaultHour: 12,
                    defaultMinute: 0
                });


            },
            error: function (e) {
                console.log("ERROR: ", e);

            },
            done: function (e) {
                console.log("DONE");
            }
        });
        $(".add_edit_icon i").click(function () {
            var data = $(this).attr("value");

            document.getElementById('etr_' + data).innerHTML = '';


            //console.log(count_schedule_element);

            for (var i = 0; i < count_schedule_element.length; i++) {
                if (count_schedule_element[i] == parseInt(data)) {
                    count_schedule_element.splice(i, 1);
                    break;
                }
            }

            console.log(count_schedule_element);


        });


    });
    add();
    function add() {

        $(".add_edit").click(function () {

            count_schedule++;
            var bus_model = '';
            var driver_name = '';
            var total_seats = '';
            var departure = '';
            var arrival = '';
            var customer = '';
            var delete_schedule = '';
            var customer_only = '';
            for (var i = 0; i < bus_list.length; i++) {
                bus_model = bus_model + '<option value="' + bus_list[i]["bus_id"] + '">' + bus_list[i]["bus_model"] + '</option>';
            }
            for (var i = 0; i < driver_list.length; i++) {
                driver_name = driver_name + '<option value="' + driver_list[i]["user_id"] + '">' + driver_list[i]["driver_name"] +
                    '</option>';
            }
            for (var i = 0; i < data_schedule[index_of]["list_cus"].length; i++) {
                customer = customer + '<option value="' + data_schedule[index_of]["list_cus"][i]["user_id"] + '">' +
                    data_schedule[index_of]["list_cus"][i]["name"] + '</option>';
            }

            bus_model = '<td id="ebus_model' + count_schedule + '"><select>' + bus_model + '</select></td>';
            driver_name = '<td id="edriver_name' + count_schedule + '"><select>' + driver_name + '</select></td>';
            total_seats = '<td id="etotal_seats' + count_schedule + '"><input type="text" placeholder="total seats"></td>';
            departure = '<td><div class="flatpickr departure  edata_departure' + count_schedule + '">' +
                '<input type="text" placeholder="Select Time" data-input  class=" input flatpickr-input active"></div></td>';
            arrival = '<td><div class="flatpickr arrival edata_arrival' + count_schedule + '">' +
                '<input type="text" placeholder="Select Time" data-input  class="input flatpickr-input active"></div></td>';
            customer = '<td ><select multiple class="ecustomer' + count_schedule + '"><option disabled>customer1</option>' +
                customer + '</select></td>';
            delete_schedule = '<td class="add_edit_icon"><i class="material-icons" value="' + count_schedule + '">delete</i></td>';
            customer_only = '<td><input type="checkbox" id="echeck' + count_schedule + '" /><label for="echeck' + count_schedule + '"></label></td>';
            tr = '<tr class="add_input" id="etr_' + count_schedule + '">' + bus_model +
                driver_name + customer + total_seats + departure + arrival + customer_only + delete_schedule + '</tr>';

            $("#edit_more_schedule").append(tr);
            console.log(count_schedule);
            $('select').material_select();


            count_schedule_element[count_schedule_element.length] = count_schedule;
            console.log(count_schedule_element);

            $(".edata_departure" + count_schedule + " input").flatpickr({
                enableTime: true,
                noCalendar: true,
                enableSeconds: false,
                time_24hr: false,
                dateFormat: "H:i",
                defaultHour: 12,
                defaultMinute: 0
            });
            $(".edata_arrival" + count_schedule + " input").flatpickr({
                enableTime: true,
                noCalendar: true,

                enableSeconds: false, // disabled by default

                time_24hr: false, // AM/PM time picker is used by default

                // default format
                dateFormat: "H:i",

                // initial values for time. don't use these to preload a date
                defaultHour: 12,
                defaultMinute: 0
            });


        });

    }

    $.ajax({
        async: false,
        cache: false,
        type: "GET",
        contentType: "application/json",
        url: "user",
        timeout: 100000,
        success: function (data) {

            var tr = '<tr>';
            var td = "";

            for (var i = 0; i < data.length; i++) {

                td = '<td>' + i + '</td>' +
                    '<td>' + data[i]["full_name"] + '</td>' +
                    '<td>' + data[i]["password"] + '</td>' +
                    '<td>' + data[i]["batch"] + '</td>' +
                    '<td>' + data[i]["email"] + '</td>' +
                    '<td>' + data[i]["gender"] + '</td>' +
                    '<td>' + data[i]["role"] + '</td>' +
                    '<td>' + data[i]["phone"] + '</td>';

                tr = tr + td + '</tr>';

            }
            document.getElementById('user').innerHTML = tr;

        },
        error: function (e) {
            console.log("ERROR: ", e);

        },
        done: function (e) {
            console.log("DONE");
        }
    });
    var bus = [];
    $.ajax({
        async: false,
        cache: false,
        type: "GET",
        contentType: "application/json",
        url: "schedule_bus",
        timeout: 100000,
        success: function (data) {
            bus = data;
            var tr = '<tr>';
            var td = "";

            for (var i = 0; i < data.length; i++) {

                td = '<td>' + (i + 1) + '</td>' +
                    '<td>' + data[i]["no_seats"] + '</td>' +
                    '<td>' + data[i]["bus_model"] + '</td>' +
                    '<td>' + data[i]["plate_number"] + '</td>' +
                    '<td>' + '<a href="#edit_bus_list" value="' + data[i]["bus_id"] +
                    '" class="edit_bus">Edit</a> / <a class="delete_bus" href="#delete_bus_list" value="' +
                    data[i]["bus_id"] + '">Delete</a>' + '</td>';


                tr = tr + td + '</tr>';

            }
            document.getElementById('schedule_bus').innerHTML = tr;

        },
        error: function (e) {
            console.log("ERROR: ", e);

        },
        done: function (e) {
            console.log("DONE");
        }
    });
    var confirm_validation;
    var edit_id;
    var delete_id;
    $(".edit_bus").click(function () {
        edit_id = $(this).attr('value');

        for (var i = 0; i < bus.length; i++) {
            if (bus[i]["bus_id"] == edit_id) {
                confirm_validation = i;
                $('#edit_num_seats').val(bus[i]["no_seats"]);
                $('#edit_bus_model').val(bus[i]["bus_model"]);
                $('#edit_plate_number').val(bus[i]["plate_number"]);
                break;
            }

        }


    });

    $(".edit_bus_btn").click(function () {
        var no = $('#edit_num_seats').val();
        var model = $('#edit_bus_model').val();
        var plate = $('#edit_plate_number').val();
        var edit = {
            "total_seats": 0,
            'bus_model': 'not_update',
            'plate_number': 'not_update',
            'bus_id': edit_id
        };
        var status = false;
        if (no != bus[confirm_validation]["no_seats"]) {
            edit["total_seats"] = parseInt(no);
            status = true;
        }
        if (model != bus[confirm_validation]["bus_model"]) {
            edit["bus_model"] = model;
            status = true;

        }
        if (plate != bus[confirm_validation]["plate_number"]) {
            edit["plate_number"] = plate;
            status = true;
        }

        if (status) {
            $.ajax({
                async: false,
                cache: false,
                contentType: 'application/json; charset=utf-8',
                type: "POST",
                data: JSON.stringify(edit),
                url: "edit_bus",
                timeout: 100000,
                success: function (data) {


                },
                error: function (e) {
                    console.log("ERROR: ", e);

                },
                done: function (e) {
                    console.log("DONE");
                }
            });

        }


    });

    $(".delete_bus").click(function () {
        delete_id = $(this).attr('value');


    });
    $(".delete_bus_btn").click(function () {
        $.ajax({
            async: false,
            cache: false,
            contentType: 'application/json; charset=utf-8',
            type: "POST",
            data: delete_id,
            url: "delete_bus",
            timeout: 100000,
            success: function (data) {


            },
            error: function (e) {
                console.log("ERROR: ", e);

            },
            done: function (e) {
                console.log("DONE");
            }
        });

    });


    var count_schedule = 0;
    var count_schedule_element = [];
    var display_set = [];
    $(".add_s").click(function () {

        var driver_list = [];
        var bus_list = [];
        $.ajax({
            async: false,
            cache: false,
            type: "GET",
            contentType: "application/json",
            url: "driver_list",
            timeout: 100000,
            success: function (data) {
                driver_list = data;
            },
            error: function (e) {
                console.log("ERROR: ", e);

            },
            done: function (e) {
                console.log("DONE");
            }
        });

        $.ajax({
            async: false,
            cache: false,
            type: "GET",
            contentType: "application/json",
            url: "schedule_bus",
            timeout: 100000,
            success: function (data) {
                bus_list = data;
            },
            error: function (e) {
                console.log("ERROR: ", e);

            },
            done: function (e) {
                console.log("DONE");
            }
        });

        var bus_model = '';
        var driver_name = '';
        var total_seats = '';
        var departure = '';
        var arrival = '';
        var customer = '';
        var delete_schedule = '';
        var customer_only = '';
        for (var i = 0; i < bus_list.length; i++) {
            bus_model = bus_model + '<option value="' + bus_list[i]["bus_id"] + '">' + bus_list[i]["bus_model"] + '</option>';
        }
        for (var i = 0; i < driver_list.length; i++) {
            driver_name = driver_name + '<option value="' + driver_list[i]["user_id"] + '">' + driver_list[i]["driver_name"] +
                '</option>';
        }
        for (var i = 0; i < data_schedule[index_of]["list_cus"].length; i++) {
            customer = customer + '<option value="' + data_schedule[index_of]["list_cus"][i]["user_id"] + '">' +
                data_schedule[index_of]["list_cus"][i]["name"] + '</option>';
        }

        bus_model = '<td id="bus_model' + count_schedule + '"><select>' + bus_model + '</select></td>';
        driver_name = '<td id="driver_name' + count_schedule + '"><select>' + driver_name + '</select></td>';
        total_seats = '<td id="total_seats' + count_schedule + '"><input type="text" placeholder="total seats"></td>';
        departure = '<td><div class="flatpickr departure  data_departure' + count_schedule + '">' +
            '<input type="text" placeholder="Select Time" data-input  class=" input flatpickr-input active"></div></td>';
        arrival = '<td><div class="flatpickr arrival data_arrival' + count_schedule + '">' +
            '<input type="text" placeholder="Select Time" data-input  class="input flatpickr-input active"></div></td>';
        customer = '<td ><select multiple class="customer' + count_schedule + '"><option disabled>customer1</option>' +
            customer + '</select></td>';
        delete_schedule = '<td class="delete_icon"><i class="material-icons" value="' + count_schedule + '">delete</i></td>';
        customer_only = '<td><input type="checkbox" id="check' + count_schedule + '" /><label for="check' + count_schedule + '"></label></td>';
        tr = '<tr class="add_input" id="tr_' + count_schedule + '">' + bus_model +
            driver_name + customer + total_seats + departure + arrival + customer_only + delete_schedule + '</tr>';


        $("#add_more_schedule").append(tr);
        $('select').material_select();


        count_schedule_element[count_schedule_element.length] = count_schedule;
        count_schedule++;

        $(".departure input").flatpickr({
            enableTime: true,
            noCalendar: true,
            enableSeconds: false,
            time_24hr: false,
            dateFormat: "H:i",
            defaultHour: 12,
            defaultMinute: 0
        });
        $(".arrival input").flatpickr({
            enableTime: true,
            noCalendar: true,

            enableSeconds: false, // disabled by default

            time_24hr: false, // AM/PM time picker is used by default

            // default format
            dateFormat: "H:i",

            // initial values for time. don't use these to preload a date
            defaultHour: 12,
            defaultMinute: 0
        });
        $(".delete_icon i").click(function () {
            var data = $(this).attr("value");

            document.getElementById('tr_' + data).innerHTML = '';


            //console.log(count_schedule_element);

            for (var i = 0; i < count_schedule_element.length; i++) {
                if (count_schedule_element[i] == parseInt(data)) {
                    count_schedule_element.splice(i, 1);
                    break;
                }
            }


            return data;

        });

    });

    $("#edite_schedule_btn").click(function () {
        var schedule_data_edit = [];
        var data_list = [];
        data_list = data_schedule[index_of]["list"];
        var assign_id = [];
        var remaining_seats = 0;
        var count_staff = 0;
        var count_user = 0;
        var count_pass = 0;
        var validate_schedule_edit = [];
        var tts = 0;
        var vbs = '';
        var not_set_cus = 0;
        var count_cus = 0;
        var conflict_driver = false;
        var conflict_bus = false;
        var vtotal_seats = '';
        var vdeparture = '';
        var varrival = '';
        var departure_time = undefined;
        var arrival_time = undefined;
        var total_seats = 0;
        var customer_only = "false";
        var validateMandatary = [];
        var str_total='';
        var bus_id = '';
        for (var i = 0; i < count_schedule_element.length; i++) {
        	str_total =$("#etotal_seats" + count_schedule_element[i] + " input").val();
        	bus_id =  $("#ebus_model" + count_schedule_element[i] + " select").val();
            if(str_total.length>0){
            	total_seats = parseInt(str_total);
            	console.log(total_seats)
            	if(!total_seats&&total_seats!=0){
            		console.log("not number");
            		total_seats =0;
            		validateMandatary[validateMandatary.length] = "Bus "+(count_schedule_element[i]+1)+ " Input Only Number";
            	}
            	else if(total_seats<=0){
            		validateMandatary[validateMandatary.length] = "Bus "+(count_schedule_element[i]+1)+ " Total Seat Should More Than 0";


            	}
            	else{
            		for(var b=0;b<bus_list.length;b++){
            			
            			if(bus_list[b]["bus_id"]==bus_id){
            				
            				if(bus_list[b]["no_seats"]<total_seats){
            					validateMandatary[validateMandatary.length] = "Bus "+
            					(count_schedule_element[i]+1)+" Should Less Than Total Seat of Bus"
            				}
            			}
            		}
            		
            	}
            }
            else{
            	total_seats = 0;
            	validateMandatary[validateMandatary.length] = "Bus "+(count_schedule_element[i]+1) +" Not Set total_seats";
            }

            var id_customer = $(".ecustomer" + count_schedule_element[i] + " select").val();
            var check = $('input:checkbox:checked#echeck' + i).val();
            if (check) {
                customer_only = "true";
                for (var j = 0; j < total_seats; j++) {
                    if (id_customer[j]) {
                        assign_id[j] = id_customer[j];


                    } else break;

                }
            } else {
                for (var j = 0; j < total_seats; j++) {
                    if (id_customer[j]) {
                        assign_id[j] = id_customer[j];

                    } else if (data_list[count_user]) {
                        if (data_list[count_user]["role"] == 'staff') {
                            count_staff++;
                        }
                        assign_id[j] = data_list[count_user]["user_id"];

                        count_user++;
                    } else break;

                }
            }
            departure_time = $('.edata_departure' + count_schedule_element[i] + ' input').val();
            arrival_time = $('.edata_arrival' + count_schedule_element[i] + ' input').val();


            remaining_seats = total_seats - assign_id.length;

            schedule_data_edit[i] = {
                "bus_id": $("#ebus_model" + count_schedule_element[i] + " select").val(),
                "driver_name": $("#edriver_name" + count_schedule_element[i] + " select").val(),
                "total_seats": total_seats,
                "passenger_id": assign_id,
                "customer": id_customer.length,
                "student": assign_id.length - id_customer.length - count_staff,
                "staff": count_staff,
                "remaining_seats": remaining_seats,
                "date_of_travel": data_schedule[index_of]["date"],
                "destination_id": data_schedule[index_of]["destination_id"],
                "est_arrival": arrival_time,
                "est_departure": departure_time,
                "customer_only": customer_only
            };

            count_cus = count_cus + id_customer.length;
            count_pass = count_pass + assign_id.length;
            count_staff = 0;
            tts = tts + parseInt($("#etotal_seats" + count_schedule_element[i] + " input").val());
            if (schedule_data_edit[i]['passenger_id'].length < 1 && schedule_data_edit[i]['total_seats'] > 0) {
                vbs += "Bus" + (1 + i) + ",";
            }

           
            if (!schedule_data_edit[i]["est_departure"]) {
                vdeparture += 'Bus' + (1 + i) + ", ";
                schedule_data_edit[i]["est_departure"] = "00:00";
            }
            if (!schedule_data_edit[i]["est_arrival"]) {
                varrival += 'Bus' + (1 + i) + ", ";
                schedule_data_edit[i]["est_arrival"] = "00:00"
            }
            not_set_cus = not_set_cus + id_customer.length;


            assign_id = [];

        }

        for (var i = 0; i < schedule_data_edit.length; i++) {
            for (var j = i + 1; j < schedule_data_edit.length; j++) {
                if (schedule_data_edit[i]["driver_name"] == schedule_data_edit[j]["driver_name"]) {
                    conflict_driver = true;
                }
                if (schedule_data_edit[i]["bus_id"] == schedule_data_edit[j]["bus_id"]) {
                    conflict_bus = true;
                }
            }
        }

        if (count_cus > data_schedule[index_of]['list_cus'].length) {
            //console.log(1+validate_schedule_edit.length+". Set Conflict customer")
            validate_schedule_edit[validate_schedule_edit.length] = "Set Conflict customer";
        }
        if (tts < data_schedule[index_of]["list"].length + data_schedule[index_of]["list_cus"].length) {

            validate_schedule_edit[validate_schedule_edit.length] = "Total Seats Not Enough For Passnger";
        }
        if (vbs) {


            validate_schedule_edit[validate_schedule_edit.length] = vbs + " Does Not Have Passnger";

        }
        if (not_set_cus < data_schedule[index_of]["list_cus"].length) {
            validate_schedule_edit[validate_schedule_edit.length] = "You Not Set Customer!";
        }
        if (conflict_bus) {
            validate_schedule_edit[validate_schedule_edit.length] = "Conflict Bus";
        }
        if (conflict_driver) {
            validate_schedule_edit[validate_schedule_edit.length] = "Conflict Driver";
        }
        
        if (vdeparture) {
            validate_schedule_edit[validate_schedule_edit.length] = vdeparture + " Not Set Departure Time";
        }
        if (varrival) {
            validate_schedule_edit[validate_schedule_edit.length] = varrival + " Not Set Arrival Time";
        }

        console.log(validate_schedule_edit);
        console.log(schedule_data_edit);
        if(validateMandatary.length>0){
        	alertMandatary(validateMandatary);
        }
        else if(schedule_data_edit.length==0){
        	validateMandatary[validateMandatary.length] = "Set At Least One Bus";
        	alertMandatary(validateMandatary);

        }
        else if (validate_schedule_edit.length > 0) {
            alertValidate(validate_schedule_edit, schedule_data_edit, "edit_schedule_passenger");
        } 

        else {
            $.ajax({
                async: false,
                cache: false,
                contentType: 'application/json; charset=utf-8',
                type: "POST",
                data: JSON.stringify(schedule_data_edit),
                url: "edit_schedule_passenger",
                timeout: 100000,
                success: function (data) {
                    if (data["status"]) {
                        $('#edit_schedule').modal('close');
                    }

                },
                error: function (e) {
                    console.log("ERROR: ", e);

                },
                done: function (e) {
                    console.log("DONE");
                }
            });

        }
        console.log("edit");
        console.log(schedule_data_edit);
    });


    $("#set_schedule").click(function () {
        var schedule_data = [];
        var data_list = [];
        data_list = data_schedule[index_of]["list"];
        var assign_id = [];
        var remaining_seats = 0;
        var count_staff = 0;
        var count_user = 0;
        var count_pass = 0;
        var total_seats=0;
        var validate_schedule = [];
        var tts = 0;
        var vbs = '';
        var not_set_cus = 0;
        var count_cus = 0;
        var conflict_driver = false;
        var conflict_bus = false;
        var vtotal_seats = '';
        var vdeparture = '';
        var varrival = '';
        var bus_id='';
        var departure_time = '';
        var arrival_time = '';
        var id_customer = [];
        var customer_only = "false";
        var validateMandatary=[];
        for (var i = 0; i < count_schedule_element.length; i++) {
        	bus_id = $("#bus_model" + count_schedule_element[i] + " select").val();
            departure_time = $('.data_departure' + count_schedule_element[i] + ' input').val();
            arrival_time = $('.data_arrival' + count_schedule_element[i] + ' input').val();
            id_customer = $(".customer" + count_schedule_element[i] + " select").val();
            var check = $('input:checkbox:checked#check' + i).val();
            if($("#total_seats" + count_schedule_element[i] + " input").val().length>0){
            	total_seats = parseInt($("#total_seats" + count_schedule_element[i] + " input").val());
            	console.log(total_seats);

            	if(!total_seats&&total_seats!=0){
            		total_seats =0;
            		validateMandatary[validateMandatary.length] = "Bus "+(count_schedule_element[i]+1)+ " Input Only Number";
            	}
            	else if(total_seats<=0){
            		validateMandatary[validateMandatary.length] = "Bus "+(count_schedule_element[i]+1)+ " Total Seat Should More Than 0";


            	}
            	else{
            		for(var b=0;b<bus_list.length;b++){
            			if(bus_list[b]["bus_id"]==bus_id){
            				console.log(bus_list[b]["no_seats"]);
            				if(bus_list[b]["no_seats"]<total_seats){
            					validateMandatary[validateMandatary.length] = "Bus "+
            					(count_schedule_element[i]+1)+" Should Less Than Total Seat of Bus"
            				}
            			}
            		}
            		
            	}
            }
            else{
            	total_seats = 0;
            	validateMandatary[validateMandatary.length] = "Bus "+(count_schedule_element[i]+1)+" Not Set total_seats";
            }

            if (check) {
                customer_only = "true";
                for (var j = 0; j < parseInt($("#total_seats" + count_schedule_element[i] + " input").val()); j++) {
                    if (id_customer[j]) {
                        assign_id[j] = id_customer[j];

                    } else break;

                }
            } else {
                for (var j = 0; j < parseInt($("#total_seats" + count_schedule_element[i] + " input").val()); j++) {
                    if (id_customer[j]) {
                        assign_id[j] = id_customer[j];

                    } else if (data_list[count_user]) {
                        if (data_list[count_user]["role"] == 'staff') {
                            count_staff++;
                        }
                        assign_id[j] = data_list[count_user]["user_id"];

                        count_user++;
                    } else break;

                }
            }


            remaining_seats = parseInt($("#total_seats" + count_schedule_element[i] + " input").val()) - assign_id.length;

            schedule_data[i] = {
                "bus_id": $("#bus_model" + count_schedule_element[i] + " select").val(),
                "driver_name": $("#driver_name" + count_schedule_element[i] + " select").val(),
                "total_seats": total_seats,
                "passenger_id": assign_id,
                "customer": id_customer.length,
                "student": assign_id.length - id_customer.length - count_staff,
                "staff": count_staff,
                "remaining_seats": remaining_seats,
                "date_of_travel": data_schedule[index_of]["date"],
                "destination_id": data_schedule[index_of]["destination_id"],
                "est_arrival": arrival_time,
                "est_departure": departure_time,
                "customer_only": customer_only
            };

            count_cus = count_cus + id_customer.length;
            count_pass = count_pass + assign_id.length;
            count_staff = 0;
            tts = tts + parseInt($("#total_seats" + count_schedule_element[i] + " input").val());
            if (schedule_data[i]['passenger_id'].length < 1 && schedule_data[i]['total_seats'] > 0) {
                vbs += "Bus" + (1 + i) + ",";
            }

            
            if (!schedule_data[i]["est_departure"]) {
                console.log(schedule_data[i]["est_departure"]);
                vdeparture += 'Bus' + (1 + i) + ", ";
                schedule_data[i]["est_departure"] = "00:00";
            }
            if (!schedule_data[i]["est_arrival"]) {
                console.log(schedule_data[i]["est_arrival"]);
                varrival += 'Bus' + (1 + i) + ", ";
                schedule_data[i]["est_arrival"] = "00:00";
            }
            not_set_cus = not_set_cus + id_customer.length;


            assign_id = [];

        }

        for (var i = 0; i < schedule_data.length; i++) {
            for (var j = i + 1; j < schedule_data.length; j++) {
                if (schedule_data[i]["driver_name"] == schedule_data[j]["driver_name"]) {
                    conflict_driver = true;
                }
                if (schedule_data[i]["bus_id"] == schedule_data[j]["bus_id"]) {
                    conflict_bus = true;
                }
            }
        }

        console.log(schedule_data);

        if (count_cus > data_schedule[index_of]['list_cus'].length) {
         
            validate_schedule[validate_schedule.length] = "Set Conflict customer";
        }
        if (tts < data_schedule[index_of]["list"].length + data_schedule[index_of]["list_cus"].length) {
            console.log(1 + validate_schedule.length + ". Total Seats Not Enough For Passnger");
            validate_schedule[validate_schedule.length] = "Total Seats Not Enough For Passnger";
        }
        if (vbs) {


            validate_schedule[validate_schedule.length] = vbs + " Does Not Have Passnger";

        }
        if (not_set_cus < data_schedule[index_of]["list_cus"].length) {
            validate_schedule[validate_schedule.length] = "You Not Set Customer!";
        }
        if (conflict_bus) {
            validate_schedule[validate_schedule.length] = "Conflict Bus";
        }
        if (conflict_driver) {
            validate_schedule[validate_schedule.length] = "Conflict Driver";
        }
        
        if (vdeparture) {
            validate_schedule[validate_schedule.length] = vdeparture + " Not Set Departure Time";
        }
        if (varrival) {
            validate_schedule[validate_schedule.length] = varrival + " Not Set Arrival Time";
        }
        if(validateMandatary.length>0){
        	alertMandatary(validateMandatary);
        }
        else if(schedule_data.length<1){
        	validateMandatary[validateMandatary.length] = "Set At Least One Bus";
        	alertMandatary(validateMandatary);

        }
        else if(validate_schedule.length > 0) {
            alertValidate(validate_schedule, schedule_data, "set_schedule_passenger");
        } else {
            $.ajax({
                async: false,
                cache: false,
                contentType: 'application/json; charset=utf-8',
                type: "POST",
                data: JSON.stringify(schedule_data),
                url: "set_schedule_passenger",
                timeout: 100000,
                success: function (data) {
                    if (data["status"]) {
                        $('#modal2').modal('close');
                    }

                },
                error: function (e) {
                    console.log("ERROR: ", e);

                },
                done: function (e) {
                    console.log("DONE");
                }
            });

        }
    });
	function alertMandatary(data){
		content = '';
		for(var i=0;i<data.length;i++){
			content = content + '<li>' + data[i] +'</li>';
		}
		content = "<ol>" + content + "</ol>";

		$.alert({
			    title: 'ERROR',
			    content: content,
			});
	}
    function alertValidate(problem, data_submit, urls) {
        var content = '';
        for (var i = 0; i < problem.length; i++) {

            content = content + '<li>' + problem[i] + '</li>'


        }
        content = '<ol>' + content + '</ol>';
        console.log(data_submit);
        $.confirm({
            title: 'ERROR',
            content: content,
            buttons: {
                confirm: function () {
                    $.ajax({
                        async: false,
                        cache: false,
                        contentType: 'application/json; charset=utf-8',
                        type: "POST",
                        data: JSON.stringify(data_submit),
                        url: urls,
                        timeout: 100000,
                        success: function (data) {
                            if (data["status"]) {
                                $('#modal2').modal('close');
                                $('#edit_schedule').modal('close');
                            }

                        },
                        error: function (e) {
                            console.log("ERROR: ", e);

                        },
                        done: function (e) {
                            console.log("DONE");
                        }
                    });

                    return true;
                },
                cancel: function () {
                    return true;
                },

            }
        });
    }


    var index_of = undefined;
    $(".set_schedule").click(function () {

        var arr = $(this).attr('value');
        if (index_of != arr) {
            count_schedule_element = [];
            document.getElementById('add_more_schedule').innerHTML = '';
        }
        index_of = arr;

        document.getElementById('date_title').innerHTML = data_schedule[index_of]['date'];
        document.getElementById('destination_title').innerHTML = data_schedule[index_of]['destination'];
        document.getElementById('remaining_seats').innerHTML = data_schedule[index_of]["list"].length +
            data_schedule[index_of]["list_cus"].length;
    });


    $(".departure input").flatpickr({
        enableTime: true,
        noCalendar: true,
        enableSeconds: false,
        time_24hr: false,
        dateFormat: "H:i",
        defaultHour: 12,
        defaultMinute: 0
    });
    $(".arrival input").flatpickr({
        enableTime: true,
        noCalendar: true,

        enableSeconds: false, // disabled by default

        time_24hr: false, // AM/PM time picker is used by default

        // default format
        dateFormat: "H:i",

        // initial values for time. don't use these to preload a date
        defaultHour: 12,
        defaultMinute: 0
    });

    $(".date_from").flatpickr({
        maxDate: "today",
        altInput: true,
    });
    $(".date_from").change(function () {
        var date_from = $(this).val();

        $(".date_to").flatpickr({
            altInput: true,
            minDate: date_from,
            maxDate: "today",

        });
    });


    $(".report_btn").click(function () {
        var from = $('.from input').val();
        var to = $('.to input').val();
        tempm = '';
        tempd = '';
        date_format = '';
        var date = new Date();

        if (date.getDate() > 9) {
            tempd = date.getDate();
        }
        else {
            tempd = "0" + date.getDate();
        }
        if (date.getMonth() + 1 > 9) {
            tempm = date.getMonth() + 1;
        }
        else {
            tempm = "0" + (date.getMonth() + 1);
        }

        console.log(date);
        if (from == "") {
            from = date.getFullYear() - 1 + "-" + tempm + "-" + tempd
        }
        if (to == "") {

            to = date.getFullYear() + "-" + tempm + "-" + tempd
        }

        var date = {
            "date_from": from,
            "date_to": to
        };
        console.log(date);
        $.ajax({
            async: false,
            cache: false,
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(date),
            url: "schedule_report",
            timeout: 100000,
            success: function (data) {
                console.log(data);

                var tr = '<tr>';
                var td = "";
                var tr_add = '<tr>';
                var td_add = '';
                var rowspan = 1;
                var tr_all = '';
                var header = '';
                var th = '';
                var bus_model = '';
                var driver_name = '';
                var student = '';
                var staff = '';
                var customer = '';
                var remaining = '';
                var total_seats = '';
                for (var i = 0; i < data.length; i++) {
                    rowspan = data[i]["mount_bus"];
                    if (rowspan == 0) {
                        rowspan = 1;
                    }
                    if (data[i]["mount_bus"] > 0) {
                        bus_model = '<td>' + data[i]["bus"][0] + '</td>';
                        driver_name = '<td>' + data[i]["driver"][0] + '</td>';
                        student = '<td>' + data[i]["student"][0] + '</td>';
                        staff = '<td>' + data[i]["staff"][0] + '</td>';
                        customer = '<td>' + data[i]["customer"][0] + '</td>';
                        remaining = '<td>' + data[i]["remaining"][0] + '</td>';
                        total_seats = '<td>' + data[i]["total_seats"][0] + '</td>';
                    }
                    else {
                        bus_model = '<td>Bus Has Deleted</td>';
                        driver_name = '<td>Driver Has Deleted</td>';
                        student = '<td>Unknown</td>';
                        staff = '<td>Unknown</td>';
                        customer = '<td>Unknown</td>';
                        remaining = '<td>Unknown</td>';
                        total_seats = '<td>Unknown</td>';

                    }

                    td = '<td rowspan="' + rowspan + '">' + data[i]["date"] + '</td>'
                        + bus_model + driver_name +
                        '<td rowspan="' + rowspan + '">' + data[i]["destination"] + '</td>' +
                        total_seats
                        + customer
                        + student
                        + staff;


                    tr = tr + td + '</tr>';
                    if (rowspan > 1) {
                        for (var j = 1; j < rowspan; j++) {

                            td_add = '<td>' + data[i]["bus"][j] + '</td>'

                                + '<td>' + data[i]["driver"][j] + '</td>'
                                + '<td>' + data[i]["total_seats"][j] + '</td>'
                                + '<td>' + data[i]["customer"][j] + '</td>'
                                + '<td>' + data[i]["student"][j] + '</td>'
                                + '<td>' + data[i]["staff"][j] + '</td>';


                            tr_add = tr_add + td_add + '</tr>';
                            td_add = '';
                        }

                    }

                    tr_all = tr_all + tr + tr_add;

                    tr_add = '<tr>';
                    tr = '<tr>';

                }

                document.getElementById('report_table').innerHTML = tr_all;


            },
            error: function (e) {
                console.log("ERROR: ", e);

            },
            done: function (e) {
                console.log("DONE");
            }
        });
    });

    $(".agree_add").click(function () {
        var bus_model = $('.add_list_bus #bus_model').val();
        var plate_number = $('.add_list_bus #plate_number').val();
        var total_seats = $('.add_list_bus #total_seats').val();
        var add = {
            "bus_model": bus_model,
            "plate_number": plate_number,
            "total_seats": parseInt(total_seats)
        };


        $.ajax({
            async: false,
            cache: false,
            contentType: 'application/json; charset=utf-8',
            type: "POST",
            data: JSON.stringify(add),
            url: "add_bus",
            timeout: 100000,
            success: function (data) {
                console.log(data);

            },
            error: function (e) {
                console.log("ERROR: ", e);

            },
            done: function (e) {
                console.log("DONE");
            }
        });


    });


    $("#pop_up_user").click(function () {
        var role = [];
        var batch = [];
        $.ajax({
            async: false,
            cache: false,
            contentType: 'application/json; charset=utf-8',
            type: "GET",

            url: "role",
            timeout: 100000,
            success: function (data) {
                role = data;

            },
            error: function (e) {
                console.log("ERROR: ", e);

            },
            done: function (e) {
                console.log("DONE");
            }
        });
        $.ajax({
            async: false,
            cache: false,
            contentType: 'application/json; charset=utf-8',
            type: "GET",

            url: "batch",
            timeout: 100000,
            success: function (data) {
                batch = data;
                console.log(batch);

            },
            error: function (e) {
                console.log("ERROR: ", e);

            },
            done: function (e) {
                console.log("DONE");
            }
        });
        var selectbatch = '<select>';
        var optionbatch = '';
        var selectrole = '<select>';
        var optionrole = '';
        for (var i = 0; i < batch.length; i++) {
            optionbatch = optionbatch + '<option value="' + batch[i]["batch_id"] + '">' + batch[i]["batch_number"] + '</option>'
        }
        for (var i = 0; i < role.length; i++) {
            optionrole = optionrole + '<option value="' + role[i]["role_id"] + '">' + role[i]["role_name"] + '</option>'
        }
        selectbatch = selectbatch + optionbatch + '</select>';
        selectrole = selectrole + optionrole + '</select>';

        document.getElementById('batch').innerHTML = selectbatch;
        document.getElementById('role').innerHTML = selectrole;
        $('select').material_select();


    });


   /* $("#form_add_user").submit(function(event){
        console.log("add");
        var name = $('#name').val();
        var batch = $('#batch select').val();
        var gender = $('#gender select').val();
        var role = $('#role select').val();
        var password = $('#password').val();
        var email = $('#email').val();
        var phone = $('#phone').val();
        var image = $('#image').val();

       


        var user = {
            "username": name,
            "batch": batch,
            "gender": gender,
            "role": role,
            "password": password,
            "email": email,
            "image": image,
            "phone_number": phone
        };
        console.log(user);

         
        event.preventDefault();

        $.ajax({
            async: false,
            cache: false,
            contentType: 'application/json; charset=utf-8',
            type: "POST",
            data: JSON.stringify(user),
            url: "add_user",
            timeout: 100000,
            success: function (data) {
                console.log(data);

            },
            error: function (e) {
                console.log("ERROR: ", e);

            },
            done: function (e) {
                console.log("DONE");
            }
        });


    });*/
   $("#form_add_user").validate({
        rules: {
            fname: {
                required: true,
                maxlength:30,
                minlength:2,
                lettersonly:true
               
            },
            lname: {
                required: true,
                maxlength:20,
                minlength:2,
                lettersonly:true
               
            },
            email: {
                required: true,
                email:true
            },
            password: {
				required: true,
				minlength: 8
			},
			gender: {
                required: true
              
            },
            role:{
            	required: true
            },
            batch: {
				required: true,
				
            },
            phone:{
            	required:true,
            	number:true,
            	minlength:9,
            	maxlength:10
            }
           },
        messages: {
            fname:{
            	maxlength:"less than 30",
                minlength:"more than 2",
                required: "enter a first name",
                lettersonly:"only letter"
                
            },
            lname:{
            	maxlength:"less than 30",
                minlength:"more than 2",
                required: "enter a last name",
                lettersonly:"only letter"
                
            },
            email:{
                required: "enter a email",
                email: "email not valid"
            },
            password: {
				required: "enter a password",
				minlength: "should be more than 8"
			},
			gender: {
                required: "select gender",
              
            },
            role:{
            	required: "select role"
            },
            batch: {
				required: "select batch",
				
            },
             phone:{
             	required:"enter phone number",
            	number:"number only",
            	minlength:"9 to 10 digit",
            	maxlength:"9 to 10 digit"
            }
          
        },
        errorElement : 'div',
        errorPlacement: function(error, element) {
          var placement = $(element).data('error');
          if (placement) {
            $(placement).append(error)
            console.log("correct");
          } else {
            error.insertAfter(element);
            console.log("incorrect");
          }
        },
        submitHandler:function(){
        	console.log("success");
       	var fname = $('#fname').val();
       	var lname = $('#lname').val();
        var batch = $('#batch select').val();
        var gender = $('#gender select').val();
        var role = $('#role select').val();
        var password = $('#password').val();
        var email = $('#email').val();
        var phone = $('#phone').val();
        var image = $('#image').val();

       


        var user = {
            "fname": fname,
            "lname": lname,
            "batch": batch,
            "gender": gender,
            "role": role,
            "password": password,
            "email": email,
            "phone_number": phone
        };
        console.log(user);

         
       

        $.ajax({
            async: false,
            cache: false,
            contentType: 'application/json; charset=utf-8',
            type: "POST",
            data: JSON.stringify(user),
            url: "add_user",
            timeout: 100000,
            success: function (data) {
                console.log(data);

            },
            error: function (e) {
                console.log("ERROR: ", e);

            },
            done: function (e) {
                console.log("DONE");
            }
        	

        })
        return false;
        }
        
     });

    $.ajax({
        async: false,
        cache: false,
        contentType: 'application/json; charset=utf-8',
        type: "GET",
        url: "emergency_show",
        timeout: 100000,
        success: function (data) {
            emergency_data = data;

            $("#view_emr").click(function () {

                var td = '';
                var tr = '';
                for (var i = 0; i < data.length; i++) {
                    td = '<td>' + data[i]["name"] + '</td>'
                        + '<td>' + data[i]["role"] + '</td>'
                        + '<td>' + data[i]["date_of_travel"] + '</td>'
                        + '<td>' + data[i]["destination"] + '</td>'
                        + '<td >' + '<a class="waves-effect waves-light modal-trigger view_reason" href="#modal_reason" value="'
                        + data[i]["emr_id"] + '">View</a>' + '</td>'
                        + '<td>' + '<input type="checkbox" value="' + i + '" id="conf' + i + '" /><label for="conf' + i + '"></label>' + '</td>';
                    tr = tr + '<tr>' + td + '</tr>';
                }

                document.getElementById('detail_emr').innerHTML = tr;
                $(".view_reason").click(function () {
                    var emr_id = $(this).attr('value');
                    for (var i = 0; i < data.length; i++) {
                        if (data[i]["emr_id"] == emr_id) {
                            document.getElementById('detail_reason').innerHTML = data[i]["reason"];
                            break;
                        }
                    }
                })
            });
            $(".confirm_btn").click(function () {

                var confirm = [];
                var reject = [];

                for (var i = 0; i < data.length; i++) {

                    var check = $('input:checkbox:checked#conf' + i).val();
                    if (check) {
                        confirm[i] = {"user_id": data[i]["user_id"], "schedule_id": data[i]["schedule_id"]};


                    }
                    else {

                        reject[i] = data[i]["user_id"];

                    }
                }
                $.ajax({
                    async: false,
                    cache: false,
                    contentType: 'application/json; charset=utf-8',
                    type: "GET",
                    url: "schedule",
                    timeout: 100000,
                    success: function (data) {
                        var total_seats = [];
                        var valid = [];
                        var status = true;
                        var confirm_schedule = [];
                        var collect_confirm = [];
                        for (var i = 0; i < data.length; i++) {
                            total_seats[i] = data[i]["total_seats"];

                            for (var j = 0; j < confirm.length; j++) {
                                if (data[i]["schedule_id"] == confirm[j]["schedule_id"]) {
                                    total_seats[i]--;
                                    collect_confirm[collect_confirm.length] = confirm[j]["user_id"];
                                }

                            }
                            confirm_schedule[i] = {
                                "schedule_id": data[i]["schedule_id"],
                                "user_id": collect_confirm
                            };
                            collect_confirm = [];
                            console.log(confirm_schedule);
                            if (total_seats[i] < 0) {
                                valid[i] = {"msg": "Not Enough Seat"};
                                status = false;
                            }
                        }
                        if (status) {
                            if (confirm_schedule.length > 0) {
                                $.ajax({
                                    async: false,
                                    cache: false,
                                    contentType: 'application/json; charset=utf-8',
                                    type: "POST",
                                    data: JSON.stringify(confirm_schedule),
                                    url: "emergency_status",
                                    timeout: 100000,
                                    success: function (data) {
                                        $('#modal_notif').modal('close');

                                    },
                                    error: function (e) {
                                        console.log("ERROR: ", e);

                                    },
                                    done: function (e) {
                                        console.log("DONE");
                                    }

                                });
                            }
                            if (reject.length > 0) {
                                $.ajax({
                                    async: false,
                                    cache: false,
                                    contentType: 'application/json; charset=utf-8',
                                    type: "POST",
                                    data: JSON.stringify(reject),
                                    url: " rejectEmergecy",
                                    timeout: 100000,
                                    success: function (data) {
                                        $('#modal_notif').modal('close');

                                    },
                                    error: function (e) {
                                        console.log("ERROR: ", e);

                                    },
                                    done: function (e) {
                                        console.log("DONE");
                                    }
                                })
                            }

                            console.log(confirm_schedule);
                            console.log(reject);

                        }
                        else {
                            validateMandatary(valid);
                        }


                    },
                    error: function (e) {
                        console.log("ERROR: ", e);

                    },
                    done: function (e) {
                        console.log("DONE");
                    }
                });

            })

        },
        error: function (e) {
            console.log("ERROR: ", e);

        },
        done: function (e) {
            console.log("DONE");
        }
    });

    function validateMandatary(problem) {
        var content = '';

        for (var i = 0; i < problem.length; i++) {

            content = content + '<li>' + problem[i][""] + '</li>'


        }
        content = '<ol>' + content + '</ol>';

        $.alert({
            title: "Wearing!",
            content: content,
        });
    }


});
