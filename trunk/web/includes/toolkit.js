function combo_load(combo_id) {
    window.eval("var combo = " + dojo.byId(combo_id).value + ";");
    if(!combo.init) {
        dojo.byId(combo_id).value = dojo.byId(combo_id).value.replace(new RegExp("init *: *false", "gi"), "init : true");
        for (item_id in combo.items) {
            dojo.byId(combo.items[item_id]).value = "false";
            dojo.byId(combo.items[item_id] + "_img").src = combo.deselect_img;
        }
        dojo.byId(combo.items[0]).value = "true";
        dojo.byId(combo.items[0] + "_img").src = combo.select_img;
    }
    combo.init = true;
}

function combo_click(combo_id, item_id) {
    window.eval("var combo = " + dojo.byId(combo_id).value + ";");
    for (item_id2 in combo.items) {
        dojo.byId(combo.items[item_id2]).value = "false";
        dojo.byId(combo.items[item_id2] + "_img").src = combo.deselect_img;
    }
    dojo.byId(item_id).value = "true";
    dojo.byId(item_id + "_img").src = combo.select_img;
}
