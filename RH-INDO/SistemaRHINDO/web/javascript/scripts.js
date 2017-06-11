/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.$('#cep').mask('99999-999');
 */


$(document).ready(function () {

    $('#cpf').mask('999.999.999-99');
    $('#cep').mask('99999-999');
    $('#celular').mask('(99) 99999-9999');
    $('#rg').mask('99.999.999-9');

    $('#btnSubmitFunc').click(function (e) {
        var isValid = true;
        $('span.clear').remove();
        $('input[type="text"]').each(function () {
            if ($.trim($(this).val()) == '') {
                isValid = false;
                $(this).css({
                    "border": "1px solid red",
                    "background": "#FFCECE"
                });

                $(this).after('<span class="clear" style="font-size:0.8em;">Campo Obrigatório</span>');
            } else {
                $(this).css({
                    "border": "",
                    "background": ""
                });
            }
        });
        $('input[type="email"]').each(function () {
            if ($.trim($(this).val()) == '') {
                isValid = false;
                $(this).css({
                    "border": "1px solid red",
                    "background": "#FFCECE"
                });

                $(this).after('<span class="clear" style="font-size:0.8em;">Campo Obrigatório</span>');
            } else {
                $(this).css({
                    "border": "",
                    "background": ""
                });
            }
        });
        $('input[type="number"]').each(function () {
            if (($.trim($(this).val()) == '')) {
                isValid = false;
                $(this).css({
                    "border": "1px solid red",
                    "background": "#FFCECE"
                });
                $(this).after('<span class="clear" style="font-size:0.8em;">Formato de número inválido</span>');
            } else {
                $(this).css({
                    "border": "",
                    "background": ""
                });
            }
        });
        $('input[type="password"]').each(function () {
            if ($.trim($(this).val()) == '') {
                isValid = false;
                $(this).css({
                    "border": "1px solid red",
                    "background": "#FFCECE"
                });

                $(this).after('<span class="clear" style="font-size:0.8em;">Campo Obrigatório</span>');
            } else {
                $(this).css({
                    "border": "",
                    "background": ""
                });
            }
        });
        if (isValid == false)
            e.preventDefault();
    });

    $('#btnSubmitDepto').click(function (e) {
        var isValid = true;
        $('span.clear').remove();
        $('input[type="text"]').each(function () {
            if ($.trim($(this).val()) == '') {
                isValid = false;
                $(this).css({
                    "border": "1px solid red",
                    "background": "#FFCECE"
                });

                $(this).after('<span class="clear" style="font-size:0.8em;">Campo Obrigatório</span>');
            } else {
                $(this).css({
                    "border": "",
                    "background": ""
                });
            }
        });
        if (isValid == false)
            e.preventDefault();
    });

    $('#btnSubmitCargo').click(function (e) {
        var isValid = true;
        $('span.clear').remove();
        $('input[type="text"]').each(function () {
            if ($.trim($(this).val()) == '') {
                isValid = false;
                $(this).css({
                    "border": "1px solid red",
                    "background": "#FFCECE"
                });

                $(this).after('<span class="clear" style="font-size:0.8em;">Campo Obrigatório</span>');
            } else {
                $(this).css({
                    "border": "",
                    "background": ""
                });
            }
        });
        $('textarea').each(function () {
            if ($.trim($(this).val()) == '') {
                isValid = false;
                $(this).css({
                    "border": "1px solid red",
                    "background": "#FFCECE"
                });

                $(this).after('<span class="clear" style="font-size:0.8em;">Campo Obrigatório</span>');
            } else {
                $(this).css({
                    "border": "",
                    "background": ""
                });
            }
        });
        $('input[type="number"]').each(function () {
            if (($.trim($(this).val()) == '')) {
                isValid = false;
                $(this).css({
                    "border": "1px solid red",
                    "background": "#FFCECE"
                });
                $(this).after('<span class="clear" style="font-size:0.8em;">Formato de número inválido</span>');
            } else {
                $(this).css({
                    "border": "",
                    "background": ""
                });
            }
        });
        if (isValid == false)
            e.preventDefault();
    });



});