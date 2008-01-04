function radio_load(radio_id) {
    window.eval("var radio = " + dojo.byId(radio_id).value + ";");
    if(!radio.init) {
        dojo.byId(radio_id).value = dojo.byId(radio_id).value.replace(new RegExp("init *: *false", "gi"), "init : true");
        for (item_id in radio.items) {
            dojo.byId(radio.items[item_id]).value = "false";
            dojo.byId(radio.items[item_id] + "_img").src = radio.deselect_img;
        }
        dojo.byId(radio.items[0]).value = "true";
        dojo.byId(radio.items[0] + "_img").src = radio.select_img;
    }
}

function radio_click(radio_id, item_id) {
    window.eval("var radio = " + dojo.byId(radio_id).value + ";");
    for (item_id2 in radio.items) {
        dojo.byId(radio.items[item_id2]).value = "false";
        dojo.byId(radio.items[item_id2] + "_img").src = radio.deselect_img;
    }
    dojo.byId(item_id).value = "true";
    dojo.byId(item_id + "_img").src = radio.select_img;
}

function check_load(check_id) {
    window.eval("var check = " + dojo.byId(check_id).value + ";");
    if(!check.init) {
        dojo.byId(check_id).value = dojo.byId(check_id).value.replace(new RegExp("init *: *false", "gi"), "init : true");
        for (item_id in check.items) {
            dojo.byId(check.items[item_id]).value = "false";
            dojo.byId(check.items[item_id] + "_img").src = check.deselect_img;
        }
    }
}

function check_click(check_id, item_id) {
    if(dojo.byId(item_id).value == "false") {
        dojo.byId(item_id).value = "true";
        dojo.byId(item_id + "_img").src = check.select_img;
    } else {
        dojo.byId(item_id).value = "false";
        dojo.byId(item_id + "_img").src = check.deselect_img;
    }
}

function sort_load(sort_id) {
    window.eval("var sort = " + dojo.byId(sort_id).value + ";");
    if(!sort.init) {
        dojo.byId(sort_id).value = dojo.byId(sort_id).value.replace(new RegExp("init *: *false", "gi"), "init : true");
        for (item_id in sort.items) {
            dojo.byId(sort.items[item_id]).value = parseInt(item_id) + 1;
        }
    }
    for (item_id in sort.items) {
        if(dojo.byId(sort.items[item_id]).value == 1) {
            dojo.byId(sort.items[item_id] + "_up_img").src = sort.up_disable_img;
        } else {
            dojo.byId(sort.items[item_id] + "_up_img").src = sort.up_enable_img;
        }
        if(dojo.byId(sort.items[item_id]).value == sort.items.length) {
            dojo.byId(sort.items[item_id] + "_down_img").src = sort.down_disable_img;
        } else {
            dojo.byId(sort.items[item_id] + "_down_img").src = sort.down_enable_img;
        }
    }
}

function sort_up_click(sort_id, item_id) {
    window.eval("var sort = " + dojo.byId(sort_id).value + ";");
    var index = dojo.byId(item_id).value;
    if(index > 1) {
        var item1 = dojo.byId(sort.items[index - 1] + "_div");
        var item2 = dojo.byId(sort.items[index - 2] + "_div");
        var temp = item1.innerHTML;
        item1.innerHTML = item2.innerHTML;
        item2.innerHTML = temp;
        for (item_id in sort.items) {
            if(dojo.byId(sort.items[item_id]).value == index) {
                dojo.byId(sort.items[item_id]).value = index - 1;
            } else if(dojo.byId(sort.items[item_id]).value == index - 1) {
                dojo.byId(sort.items[item_id]).value = index;
            }
            if(dojo.byId(sort.items[item_id]).value == 1) {
                dojo.byId(sort.items[item_id] + "_up_img").src = sort.up_disable_img;
            } else {
                dojo.byId(sort.items[item_id] + "_up_img").src = sort.up_enable_img;
            }
            if(dojo.byId(sort.items[item_id]).value == sort.items.length) {
                dojo.byId(sort.items[item_id] + "_down_img").src = sort.down_disable_img;
            } else {
                dojo.byId(sort.items[item_id] + "_down_img").src = sort.down_enable_img;
            }
        }
    }
}

function sort_down_click(sort_id, item_id) {
    window.eval("var sort = " + dojo.byId(sort_id).value + ";");
    var index = dojo.byId(item_id).value;
    if(index < sort.items.length) {
        var item1 = dojo.byId(sort.items[index - 1] + "_div");
        var item2 = dojo.byId(sort.items[index] + "_div");
        var temp = item1.innerHTML;
        item1.innerHTML = item2.innerHTML;
        item2.innerHTML = temp;
        for (item_id in sort.items) {
            if(dojo.byId(sort.items[item_id]).value == index) {
                dojo.byId(sort.items[item_id]).value = parseInt(index) + 1;
            } else if(dojo.byId(sort.items[item_id]).value == parseInt(index) + 1) {
                dojo.byId(sort.items[item_id]).value = index;
            }
            if(dojo.byId(sort.items[item_id]).value == 1) {
                dojo.byId(sort.items[item_id] + "_up_img").src = sort.up_disable_img;
            } else {
                dojo.byId(sort.items[item_id] + "_up_img").src = sort.up_enable_img;
            }
            if(dojo.byId(sort.items[item_id]).value == sort.items.length) {
                dojo.byId(sort.items[item_id] + "_down_img").src = sort.down_disable_img;
            } else {
                dojo.byId(sort.items[item_id] + "_down_img").src = sort.down_enable_img;
            }
        }
    }
}

function text_load(text_id) {
    window.eval("var text = " + dojo.byId(text_id).value + ";");
    if(!text.init) {
        dojo.byId(text_id).value = dojo.byId(text_id).value.replace(new RegExp("init *: *false", "gi"), "init : true");
    }
    dojo.byId(text_id + "_field").value = text.value;
}

function text_press(text_id) {
    window.eval("var text = " + dojo.byId(text_id).value + ";");
    dojo.byId(text_id).value = dojo.byId(text_id).value.replace(new RegExp("value *: *'" + text.value + "'", "gi"), "value : '" + dojo.byId(text_id + "_field").value + "'");
    text.value = dojo.byId(text_id + "_field").value;
}

function coldiv_load(coldiv_id) {
    window.eval("var coldiv = " + dojo.byId(coldiv_id).value + ";");
    if(!coldiv.init) {
        dojo.byId(coldiv_id).value = dojo.byId(coldiv_id).value.replace(new RegExp("init *: *false", "gi"), "init : true");
        dojo.byId(coldiv_id + "_img").src = coldiv.col_img;
        dojo.byId(coldiv_id + "_div").style.display = "none";
    }
}

function coldiv_click(coldiv_id) {
    window.eval("var coldiv = " + dojo.byId(coldiv_id).value + ";");
    if (dojo.byId(coldiv_id + "_div").style.display == "block") {
        dojo.byId(coldiv_id + "_img").src = coldiv.col_img;
        dojo.byId(coldiv_id + "_div").style.display = "none";
    } else {
        dojo.byId(coldiv_id + "_img").src = coldiv.uncol_img;
        dojo.byId(coldiv_id + "_div").style.display = "block";
    }
}

function show(id) {
    dojo.byId(id).style.display = "block";
}

function hide(id) {
    dojo.byId(id).style.display = "none";
}