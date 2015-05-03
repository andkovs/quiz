// objects
var testsJson;
var currentTestName;
var currentTestCategoryName;
var questionsJson;
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
var testTitleLabel;
var testTimesLabel;
var testForm;
var testPaging;
var sendBtn;
// result
var resultBlock;
var resultName;
var resultTest;
var resultCorrect;
var resultUncorrect;
var userName;
var userSurname;
var testId;
var answers = [];

$(function(){

    // topics
    topicsBlock = $('#topicsBlock');
    topicsList = $('#topicsList');
    topicsList.on('click', '.chooseTest', function(){
        currentTestName = $(this).text();
        currentTestCategoryName = $(this).parent().prev().text();
        testTitleLabel.text(currentTestCategoryName + ": " + currentTestName);
        testId = $(this).data('test');
        getQuestions(testId);
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
            startTimer();
        }
    });
    loginCancelBtn = $('#loginCancel');
    loginCancelBtn.on('click', function(){
        loginBlock.hide(0);
        topicsBlock.show(0);
    });

    // test
    testBlock = $('#testBlock');
    testTitleLabel = $('#testTitle');
    testTimesLabel = $('#testTime');
    testForm = $('#testForm');
    testPaging = $('#testPaging');
    sendBtn = $('#send');
    sendBtn.on('click', function(){
        sendResult();
    })

    // result
    resultBlock = $('#resultBlock');
    resultName = $('#resultName');
    resultTest = $('#resultTest');
    resultCorrect = $('#resultCorrect');
    resultUncorrect = $('#resultUncorrect');
});

function getSubjects(){
    $.ajax({
        type: "GET",
        url: "rest/test/subjects",
        success: function(data){
            testsJson = data;
            $.each(testsJson, function(index, elem){
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
            questionsJson = data;
            $.each(questionsJson, function(){
                $('#testQuestionTmpl').tmpl(this).appendTo(testForm);
            });
            switchQuestion(0);
        }
    })
}

function startTimer() {
    var time = 15 * 60;
    testTimesLabel.text(timeText(time));
    var timeInterval = setInterval(function(){
        time--;
        if (time >= 0) {
            testTimesLabel.text(timeText(time));
        }
        else {
            clearInterval(timeInterval)
            sendResult();
        }
    }, 1000);
};

function timeText(time) {
    var seconds = time % 60;
    var minutes = Math.floor(time/60);
    var result = "";
    if (seconds >= 10)
        result = minutes + ":" + seconds;
    else
        result = minutes + ":0" + seconds;
    return result;
};

function switchQuestion(index){
    var questionsCount = questionsJson.length;

    if (index == "prev" && currentQuestionIndex > 0) {
        currentQuestionIndex--;
    } else  if (index == "next" && currentQuestionIndex < (questionsCount - 1)) {
        currentQuestionIndex++;
    } else if (typeof index == "number" && index >= 0 && index < questionsCount) {
        currentQuestionIndex = index;
    }

    var formQuestions = testForm.find('fieldset');
    formQuestions.hide(0);
    $(formQuestions[currentQuestionIndex]).show(0);
    testPaging.find('.btn').removeClass('btn-primary');
    $(testPaging.find('.btn').not(':first')[currentQuestionIndex]).addClass('btn-primary');
};

function sendResult() {
    var questionSections = testForm.find('fieldset');
    var testAnswers = [];
    questionSections.each(function () {
        var answerSubjectId = parseInt($(this).data('subject'));
        var answerQuestionId = parseInt($(this).data('question'));
        var answerId = 0;
        var selectedAnswerElem = $(this).find('[type="radio"]:checked');
        if (selectedAnswerElem.length != 0)
            answerId = parseInt(selectedAnswerElem.val());
        var answer = {answerId: answerId, questionId: answerQuestionId, subjectId: answerSubjectId};
        testAnswers.push(answer);
    });
    console.log('testAnswers', testAnswers);
    var dataToSend = JSON.stringify({
        subjectId: testId,
        name: userName,
        surname: userSurname,
        answers: testAnswers
    });
    console.log('dataToSend', dataToSend);
    $.ajax({
        type: "POST",
        url: "rest/test/answers",
        //dataType: "json",
        contentType: "application/json",
        data: dataToSend,
        success: function(data){
            console.log("success data from send result", data);
            testBlock.hide(0);
            resultBlock.show(0);
            resultName.text(userName + " " + userSurname);
            resultTest.text(currentTestCategoryName + ": " + currentTestName);
            resultCorrect.text(data.correctAnswers);
            if (data.questionsWithUncorrectAnswers.length == 0) {
                resultUncorrect.html("<p>0</p>");
            } else {
                var uncorrectList = $(document.createElement("ul"));
                resultUncorrect.html(uncorrectList);
                $.each(data.questionsWithUncorrectAnswers, function(index, elem){
                    var uncorrectAnswer = $(document.createElement("li"));
                    uncorrectAnswer.html("<b>" + elem.needQuestion + "</b></br />" + elem.answer);
                    uncorrectList.append(uncorrectAnswer);
                });
            }
        },
        error: function(data) {
            console.log("error data form send result ", data);
            testBlock.hide(0);
            resultBlock.show(0);
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

