// objects
var topicsJson;
var testJson;
var currentQuestionIndex = 0;
// topics
var topicsBlock;
var topicsList;
// login
var loginBlock;
var firstNameInput;
var lastNameInput;
var loginSubmitBtn;
var loginCancelBtn;
// test
var testBlock;
var testTitle;
var testForm;
var testPaging;
var sendBtn;
// result
var resultBlock;
var userName;
var userSurname;
var subjectId;
var answers = [];

$(function(){

    // topics
    topicsBlock = $('#topicsBlock');
    topicsList = $('#topicsList');
    topicsList.on('click', '.chooseTest', function(){
        subjectId = $(this).data('test');
        getQuestions(subjectId);
        topicsBlock.hide(0);
        loginBlock.show(0);
    });

    getSubjects();

    // login
    loginBlock = $('#loginBlock');
    firstNameInput = $('#firstName');
    lastNameInput = $('#lastName');
    loginSubmitBtn = $('#loginSubmit');
    loginSubmitBtn.on('click', function(){
        if (firstNameInput.val() != "" && lastNameInput.val() != "") {
            userName = firstNameInput.val();
            userSurname = lastNameInput.val();
            loginBlock.hide(0);
            testBlock.show(0);
        }
    });
    loginCancelBtn = $('#loginCancel');
    loginCancelBtn.on('click', function(){
        loginBlock.hide(0);
        topicsBlock.show(0);
    });

    // test
    testBlock = $('#testBlock');
    testTitle = testBlock.find('h2');
    testForm = $('#testForm');
    testPaging = $('#testPaging');
    sendBtn = $('#send');
    sendBtn.on('click', function(){
        sendResult();
    })

    // result
    resultBlock = $('#resultBlock');
});

function getSubjects(){
    $.ajax({
        type: "GET",
        url: "rest/test/subjects",
        success: function(data){
            topicsJson = data;
            console.log('topics json', topicsJson);
            $.each(topicsJson, function(index, elem){
                var topicHtml = '<div class="panel panel-primary"><div class="panel-heading">' + elem.name + '</div><ul class="list-group">';
                if (elem.childSubject != null) {
                    $.each(elem.childSubject, function(index, elem){
                        topicHtml += '<a class="list-group-item chooseTest" data-test="' + elem.childSubjectId +'">' + elem.childSubjectName +'</a>'
                    });
                }
                topicHtml += "</ul></div>";
                topicsList.append(topicHtml);
            });
        }
    })
}

function getQuestions(id){
    $.ajax({
        type: "GET",
        url: "rest/test/questions/" + id,
        success: function(data){
            testJson = data;
            console.log('test json', testJson);
            testTitle.text(testJson[0].subjectId);
            $.each(testJson, function(){
                $('#testQuestionTmpl').tmpl(this).appendTo(testForm);
            });
            //testPaging.html($('#testPagingTmpl').tmpl(testJson));
            //switchQuestion(0);
        }
    })
}

function switchQuestion(index){
    questionsCount = 10;
    if (index == "prev" && currentQuestionIndex > 0) {
        currentQuestionIndex--;
    } else  if (index == "next" && currentQuestionIndex < (questionsCount - 1)) {
        currentQuestionIndex++;
    } else if (typeof index == "number" && index >= 0 && index < questionsCount) {
        currentQuestionIndex = index;
    } else {
        currentQuestionIndex = 0;
    }
    var formQuestions = testForm.find('fieldset');
    formQuestions.hide(0);
    $(formQuestions[currentQuestionIndex]).show(0);
};

function sendResult() {
    var questionSections = testForm.find('fieldset');
    var dataToSend = JSON.stringify({
        subjectId: 1,
        name: userName,
        surname: userSurname,
        answers: []
    });
    $.ajax({
        type: "POST",
        url: "rest/test/answers",
        //dataType: "json",
        contentType: "application/json",
        data: result,
        success: function(data){
            console.log("success data from send result", data)
        },
        error: function(data) {
            console.log("error data form send result ", data);
        }
    })
}

//function sendResult(){
//    var result = JSON.stringify({
//        subjectId: subjectId,
//        name: userName,
//        surname: userSurname,
//        answers: answers
//    });
//    console.log('test results to send', result);
//    $.ajax({
//        type: "POST",
//        url: "rest/test/answers",
//        //dataType: "json",
//        contentType: "application/json",
//        data: result,
//        success: function(data){
//            console.log("success data from send result", data)
//        },
//        error: function(data) {
//            console.log("error data form send result ", data);
//        }
//    })
//}

//function readAnswer (data, index) {
//    var answId = $('input[name="answer"]:checked').val();
//    answers[index] = {subjectId: subjectId, answerId : answId, questionId : data[index].id};
//}

//function findAnswers(id) {
//    for (var i = 0; i < answers.length; i++) {
//        var ans = answers[i];
//        if(ans.questionId == id){
//            return ans.answerId;
//        }
//    }
//}

//function showQuestion(data, index) {
//    var elem = data[index];
//    if(index< data.length) {
//        $("#testForm").empty();
//        var fieldSet = $(document.createElement('fieldset'));
//        $("#testForm").append(fieldSet);
//        var label = $(document.createElement('legend'));
//        label.text(elem.question);
//        fieldSet.append(label);
//        fieldSet.addClass("question");
//        fieldSet.addClass("active");
//        var ansId = findAnswers(data[index].id);
//        $.each(elem.answer, function (indexAnswer, elemAnswer) {
//            var answerDiv = $(document.createElement('div'));
//            //console.log(answerDiv);
//            answerDiv.addClass("radio");
//            var radioInput = $(document.createElement('input'));
//            var radioLabel = $(document.createElement('label'));
//            radioInput.attr("type", "radio");
//            radioInput.attr("name", "answer");
//            if(ansId == elemAnswer.answerId){
//                radioInput.attr("checked", "checked");
//            }
//            radioInput.attr("value", elemAnswer.answerId);
//            radioLabel.append(radioInput);
//            radioLabel.append(elemAnswer.answer);
//            answerDiv.append(radioLabel);
//            fieldSet.append(answerDiv);
//        });
//    }
//    $('input[name="answer"]:radio').change(function () {
//        readAnswer(data, index)
//    })
//
//    if (index + 1 < data.length) {
//        nextBtn.off("click")
//        nextBtn.click(function () {
//            showQuestion(data, index + 1);
//        });
//        nextBtn.prop("disabled",false);
//    } else {
//        nextBtn.prop("disabled",true);
//    }
//    if (index - 1 >= 0) {
//        prevBtn.off("click");
//        prevBtn.click( function () {
//            showQuestion(data, index - 1);
//        });
//
//        prevBtn.prop("disabled",false);
//    } else {
//        prevBtn.prop("disabled",true);
//    }
//}

