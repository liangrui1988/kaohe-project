'use strict';

var placeholders = document.querySelectorAll('.styled-input__placeholder-text'),
    inputs = document.querySelectorAll('.styled-input__input');

placeholders.forEach(function (el, i) {
    var value = el.innerText,
        html = '';
    for (var v_iterator = value, v_isArray = Array.isArray(v_iterator), v_i = 0, v_iterator = v_isArray ? v_iterator : v_iterator[Symbol.iterator]();;) {
        var v_ref;

        if (v_isArray) {
            if (v_i >= v_iterator.length) break;
            v_ref = v_iterator[v_i++];
        } else {
            v_i = v_iterator.next();
            if (v_i.done) break;
            v_ref = v_i.value;
        }

        var w = v_ref;

        if (!value) value = '&nbsp;';
        html += '<span class="letter">' + w + '</span>';
    }
    el.innerHTML = html;
});

inputs.forEach(function (el) {
    var parent = el.parentNode;
    el.addEventListener('focus', function () {
        parent.classList.add('filled');
        placeholderAnimationIn(parent, true);
    }, false);
    el.addEventListener('blur', function () {
        if (el.value.length) return;
        parent.classList.remove('filled');
        placeholderAnimationIn(parent, false);
    }, false);
});

function placeholderAnimationIn(parent, action) {
    var act = action ? 'add' : 'remove';
    var letters = parent.querySelectorAll('.letter');
    letters = [].slice.call(letters, 0);
    if (!action) letters = letters.reverse();
    letters.forEach(function (el, i) {
        setTimeout(function () {
            var contains = parent.classList.contains('filled');
            if (action && !contains || !action && contains) return;
            el.classList[act]('active');
        }, 50 * i);
    });
}

setTimeout(function () {
    document.body.classList.add('on-start');
}, 100);

setTimeout(function () {
    document.body.classList.add('document-loaded');
}, 1800);