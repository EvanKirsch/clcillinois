var pageUser;

function login(event) {
    var username = document.getElementById("usernameInput").value;
    var password = document.getElementById("passwordInput").value;
    tasker.login( username, password, loginCb );
    event.preventDefault();
    return false;
}

function loginCb(user, error) {
    if (error) {
        return error;
    }    
    document.getElementById("login").setAttribute("class", "off");
    document.getElementById("home").setAttribute("class", "on");
    document.getElementById("logout").innerHTML = "Welcome " + document.getElementById("usernameInput").value;
    pageUser = user;
    tasker.tasks(user.id, tasksCb);
}

function logout() {
    tasker.logout(logoutCb);
}

function logoutCb(){
    document.getElementById("login").setAttribute("class", "on");
    document.getElementById("home").setAttribute("class", "off");
    document.getElementById("usernameInput").value = null;
    document.getElementById("passwordInput").value = null;
    pageUser = null;
    var table = document.getElementById("tasks");
    while (table.firstChild) {
        table.removeChild(table.firstChild);
    }
}

function addTask() {
    var addOwnerId = pageUser.id;
    var addDesc = document.getElementById("descField").value;
    var addDue = document.getElementById("dField").value;
    var addColor = document.getElementById("cField").value;
    var addTask = {ownerId: addOwnerId, desc: addDesc, due: addDue, color: addColor, complete: false }
    tasker.add(addOwnerId, addTask, addCb);
}

function tasksCb(taskList, error) {
    if (!taskList) {
        console.log(error);
        return error;
    }
    
    var t = document.getElementById("tasks");
    var headRow = document.createElement('tr');
    var thDisc = document.createElement('th');
    var thColor = document.createElement('th');
    var thDue = document.createElement('th');
    var thComplete = document.createElement('th');
    var thDelete = document.createElement('th');
    
    thDisc.appendChild(document.createTextNode("Description"));
    thColor.appendChild(document.createTextNode("Color"));
    thDue.appendChild(document.createTextNode("Due"));
    thComplete.appendChild(document.createTextNode("Completed"));
    
    headRow.appendChild(thDisc);
    headRow.appendChild(thColor);
    headRow.appendChild(thDue);
    headRow.appendChild(thComplete);
    headRow.appendChild(thDelete);
    t.appendChild(headRow);

    taskList.forEach( x => {
        printTask(x);
    });
}

function addCb(task, error) {
    if (error) {
        return error;
    }
    printTask(task);
}

function printTask(task) {
    var t = document.getElementById("tasks");
    var row = document.createElement('tr')
    var newRow = updateRow(row, task);
    row.setAttribute("id", task.id);
    row.setAttribute("class", "tableRow");
    t.appendChild(row)
}

function updateRow (row, task) {
    while (row.firstChild) {
        row.removeChild(row.firstChild);
    }
    var tdDis = document.createElement('td');
    row.setAttribute("id", task.id);
    row.setAttribute("class", "tableRow");
    var tdColor = document.createElement('td');
    var colorSelector = document.createElement('input');
    colorSelector.setAttribute("type", "color");
    colorSelector.setAttribute("value", task.color);
    colorSelector.setAttribute("name", "color");
    colorSelector.addEventListener("change", x => editColor(task, colorSelector.value));
    tdColor.appendChild(colorSelector);

    var tdDate = document.createElement('td');
    var tdComplete = document.createElement('td');
    
    var done = document.createElement('input');
    done.addEventListener("click", x=> editComplete(task));
    done.setAttribute("type", "checkbox");
    if (task.complete) {
        done.setAttribute("checked", task.complete);
    }
    tdComplete.appendChild(done);
    if (!task.complete && isPast(task.due)) {
        var overDue = document.createElement('span');
        overDue.innerHTML = "!";
        overDue.setAttribute('class', "overdueIcon");
        tdComplete.appendChild(overDue);
    }

    var tdDelete  = document.createElement('td');
    tdDelete.addEventListener("click", x => remove(task));

    tdDis.innerHTML = task.desc;                    

    var date = new Date(task.due);
    var printDate = date.getDate() + "/" + date.getMonth() + "/" + date.getFullYear();
    tdDate.innerHTML = printDate;                   

    tdDis.setAttribute("class", "tdDis");
    tdColor.setAttribute("class", "tdColor");
    tdDate.setAttribute("class", "tdDate");
    tdComplete.setAttribute("class", "tdComplete");
    tdDelete.setAttribute("class", "tdDelete");

    row.appendChild(tdDis);
    row.appendChild(tdColor);
    row.appendChild(tdDate);
    row.appendChild(tdComplete);
    row.appendChild(tdDelete);
    return row;
}

function editComplete(task) {
    var upComp;
    if (task.complete == true) {
        upComp = {"complete" : false};
    } else {
        upComp = {"complete" : true};
    }
    tasker.edit( task.ownerId , task.id, upComp, editCb);
}

function editColor(task, color) {
    var upColor = {"color" : color};
    tasker.edit(task.ownerId , task.id, upColor, editCb);
}

function editCb(task, error) {
    if (error) {
        console.log(error);
        return error;
    }
    var newRow = document.getElementById(task.id);
    updateRow(newRow, task);
}


function remove(task) {
    tasker.delete(task.ownerId, task.id, deleteCB);
    event.preventDefault();
    return false;
}

function deleteCB(task, error) {
    if (error) {
        console.log(error);
        return error;
    }
    var r = document.getElementById(task.id);
    while (r.firstChild) {
        r.removeChild(r.firstChild);
    }
    document.getElementById("tasks").removeChild(r);
}

function isPast(date) {
    var today = new Date();
    if (today > date) {
        return true;
    } else {
        return false;
    }
}

function incompleteChange() {
    clearTable();
    var check = document.getElementById("incompleteBox").checked;
    if (check) {
        tasker.tasks(pageUser.id, incompleteChangeCb);
    } else {
        if (document.getElementById("overdueBox").checked) {
            tasker.tasks(pageUser.id, overdueChangeCb);  
        } else {
            tasker.tasks(pageUser.id, defaultPrintTableCb);
        }
    }
}

function overdueChange() {
    clearTable();
    var check = document.getElementById("overdueBox").checked;
    if (check) {
        tasker.tasks(pageUser.id, overdueChangeCb);
    } else {
        if (document.getElementById("incompleteBox").checked) {
            tasker.tasks(pageUser.id, incompleteChangeCb);  
        } else {
            tasker.tasks(pageUser.id, defaultPrintTableCb);
        }
    }
}

function clearTable() {
    var table = document.getElementById("tasks");
    var tempRow = table.firstChild.nextSibling;
    while (tempRow.nextSibling) {
        table.removeChild(tempRow.nextSibling);
    }
}

function incompleteChangeCb(taskList, error) {
    if (error) {
        return error;
    }
    taskList.forEach(x => {
        if (!x.complete) {
            printTask(x);
        }
    });
}

function overdueChangeCb(taskList, error) {
    if (error) {
        return error;
    }
    taskList.forEach( x => {
        if(!x.complete && isPast(x.due)) {
            printTask(x);
        }
    });
}

function defaultPrintTableCb(taskList, error) {
    if (error) {
        return error;
    }
    taskList.forEach(x => {
            printTask(x);
        }
    );
}

function searchTextChange() {
    clearTable();
    tasker.tasks(pageUser.id, searchCb);
}

function searchCb(taskList, error) {
    if (error) {
        return error;
    }
    var txt = document.getElementById("searchField").value;
    taskList.forEach( x => {
        var temp = x.desc;
        var overdue = document.getElementById("overdueBox").checked;
        var incomplete = document.getElementById("incompleteBox").checked;
        console.log(temp);
        if (overdue) {
            if (temp.includes(txt) &&  !x.complete && isPast(x.due)) {
                printTask(x);
            }
        } else if (incomplete) {
            if (temp.includes(txt) && !x.complete) {
                printTask(x);
            }

        } else {
            if (temp.includes(txt)) {
                printTask(x);
            }
        }
    })
}