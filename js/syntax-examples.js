function risk(height, weight, sex) {
    var bmi = (weight*0.45) / Math.pow((height*0.025), 2);
    if ("M" && (bmi > 31.9 || bmi < 20.4)){
        return true;
    } else if ("F" && (bmi > 27.6 || bmi < 19.4)) {
        return true;
    }
    return false;
}

function roman(number) {
    var romanNums = {'i':1, 'ii':2, 'iii':3, 'iv':4, 'v':5, 'vi':6, 'vii':7, 'viii':8, 'ix':9, 'x':10};
    var regNums = {1:'I', 2:'II', 3:'III', 4:'IV', 5:'V', 6:'VI', 7:'VII', 8:'VIII', 9:'IX', 10:'X'};
    if (typeof number == 'string') {
        number =  number.toLowerCase();
        return(romanNums.number);
    } else if(typeof number == 'number') {
        return(regNums.number);
    } else {
        return undefined;
    } 
}

function lettersThatFollow(text, ch) {
    var ans = "";
    for (var i = 0; i < text.length; i++) {
        if (text[i] == ch && !ans.includes(text[i+1])) {
            ans = ans + text[i+1];
        }
    }
    if (ans == "") {
        console.log(undefined);
        return undefined;
    } else {
        return ans;
    }
}

function props(list, propertyName) {
    var ans = new Array(list.length);
    for (var i = 0; i < list.length; i++) {
        ans[i] = list[i][propertyName];
    }

    return ans;
}

function listify(item, listType) {
    function readArray(item, listType, ans) {
        ans = ans + "<" + listType + ">";
        for (var i = 0; i < item.length; i++) {
            ans += "<li>";
            if (item[i] instanceof Array) {
                ans += item[i].shift();
                ans = readArray(item[i], listType, ans) + "</li><li>";
                i++;
            }
            ans += item[i];
            ans += "</li>";
        }
        ans = ans + "</" + listType + ">";
        return ans;
    }
    if (listType != "ol") {
        listType = "ul";
    }
    var ans = '';
    ans = readArray(item, listType, ans);
    return ans;
}

function cashier(price, payment) {
    var totChange = { twenties : 0, tens : 0, ones : 0, quarters : 0, dimes : 0, nickels : 0, pennies : 0 };
    var changeDue = Math.abs(payment - price).toFixed(2).toString();
    changeDue = changeDue.split(".");
    var dol = changeDue[0];
    var cent = changeDue[1];
    totChange.twenties = Math.floor(dol / 20);
    dol = dol % 20;
    totChange.tens = Math.floor(dol / 10);
    dol = dol % 10;
    totChange.ones = Math.floor(dol / 1);
    dol = dol % 1;
    totChange.quarters = Math.floor(cent / 25);
    cent = cent % .25;
    totChange.dimes = Math.floor(cent / 10);
    cent = cent % .10;
    totChange.nickels = Math.floor(cent / 5);
    cent = cent % .05;
    totChange.pennies = Math.floor(cent / 1);
    cent = cent % .01;
    return totChange;
}

function repeat(text, n){
    var ans = "";
    for (var i = 0; i < n; i++) {
        ans += text;
    }
    return ans;
}

function repeatf(f, n) {
    var ans = new Array(n);
    for (var i = 0; i < n; i++) {
        ans[i] = f();
        if (ans[i] < 0) {
            return [];
        }
    }
    return ans;
}

function matchmaker(obj) {
    function compareTo(obj){
        for (var i in n) {
            if (!(obj[i] == n[i])) {
                return false;
            }
        }
        return true;
    }
    var n = obj;
    return compareTo;
}

function breakup(list, partitiner) {
    var ans = {};
    var temp;
    for (var i in list) {
        temp = partitiner(list[i]);
        if (!(temp in ans)) {
            ans[temp] = [i];
        } else {
            ans[temp].push(i);
        }
    }
    return ans;
}

function eachOne(list) {
    for (var i in list) {
        if (!list[i]) {
            return list[i];
        }
    }
    return true;
}

function noSql(list, query) {
    var ans = [];
    for (var obj in list) {
        if (query === list[obj]) {
            console.log("pusing");
            ans.push(list[n]);
        }
        for (var n in list[obj]) {
            for (var q in query) {
                if (query[q] === list[obj][n] && Object.keys(query).length == 1) {
                    ans.push(list[obj]);
                }
            }
        }
    }
    
    return ans;
}

function justOnce(f) {
    var calls = 0;
    var ans;
    function done() {
        if (calls < 1) {
            ans = f.apply(this,arguments);
        }
        calls++;
        return ans;
    }
    return done;
}