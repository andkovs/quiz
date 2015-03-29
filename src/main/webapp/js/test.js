// objects

var topicsJson;
var testJson;
// login
var loginBlock;
var firstNameInput;
var lastNameInput;
var startBtn;
// topics
var topicsBlock;
var topicsList;
// test
var testBlock;
var testTitle;
var testForm;
var questions;
var nextBtn;
var prevBtn;
var sendBtn;


// result
var resultBlock;
var userName;
var userSurname;
var subjectId;
var answers = [];

$(function(){

    // login
    loginBlock = $('#loginBlock');
    firstNameInput = $('#firstName');
    lastNameInput = $('#lastName');
    startBtn = $('#start');
    startBtn.on('click', function(){
        if (firstNameInput.val() != "" && lastNameInput.val() != "") {
            userName = firstNameInput.val();
            userSurname = lastNameInput.val();
            getSubjects(createSubjectsList);
            loginBlock.hide(0);
            topicsBlock.show(0);
        }
    });

    // topics
    topicsBlock = $('#topicsBlock');
    topicsList = $('#topicsList');
    topicsList.on('click', '.chooseTest', function(){
        subjectId = $(this).data('test');
        getQuestions(subjectId, createQuestionsQuiz);
    });

    // test
    testBlock = $('#testBlock');
    testTitle = testBlock.find('h2');
    testForm = $('#testForm');
    prevBtn = $('#prev');
    prevBtn.on('click', function(){
        switchQuestion(-1);
    });
    nextBtn = $('#next');
    nextBtn.on('click', function(){
        switchQuestion(1);
    });
    sendBtn = $('#send');
    sendBtn.on('click', function(){
        // get test result
    });

    // result
    resultBlock = $('#resultBlock');

    sendBtn.on('click', function(){
        sendResult();
    })
});

function getTest(id){
    testJson = {
        "name": "test " + id,
        "questions": [
            {
                "name": "Question 1",
                "answers": [
                    { "id": 1, "name": "Answer 1" },
                    { "id": 2, "name": "Answer 2" },
                    { "id": 3, "name": "Answer 3" },
                    { "id": 4, "name": "Answer 4" }
                ]
            },
            {
                "name": "Question 2",
                "answers": [
                    { "id": 1, "name": "Answer 1" },
                    { "id": 2, "name": "Answer 2" },
                    { "id": 3, "name": "Answer 3" },
                    { "id": 4, "name": "Answer 4" }
                ]
            },
            {
                "name": "Question 3",
                "answers": [
                    { "id": 1, "name": "Answer 1" },
                    { "id": 2, "name": "Answer 2" },
                    { "id": 3, "name": "Answer 3" },
                    { "id": 4, "name": "Answer 4" }
                ]
            },
            {
                "name": "Question 4",
                "answers": [
                    { "id": 1, "name": "Answer 1" },
                    { "id": 2, "name": "Answer 2" },
                    { "id": 3, "name": "Answer 3" },
                    { "id": 4, "name": "Answer 4" }
                ]
            },
            {
                "name": "Question 5",
                "answers": [
                    { "id": 1, "name": "Answer 1" },
                    { "id": 2, "name": "Answer 2" },
                    { "id": 3, "name": "Answer 3" },
                    { "id": 4, "name": "Answer 4" }
                ]
            }
        ]
    };
    testTitle.text(testJson.name);
    // test html
    topicsBlock.hide(0);
    testBlock.show(0);
};

function switchQuestion(step){

};


function getSubjects(callback){
    $.ajax({
        type: "GET",
        url: "rest/test/subjects",
        success: function(data){
            callback(data)
        }
    })
}

function createSubjectsList(data){
    //todo: parse data and show
    console.log(data);
    $.each(data, function(index, elem){
        var topicHtml = '<div class="panel-heading">' + elem.name + '</divl><div class="panel-body"><ul>';
        if (elem.childSubject != null) {
            $.each(elem.childSubject, function(index, elem){
                topicHtml += '<li class="chooseTest" data-test="' + elem.id +'">' + elem.name +'</li>'
            });
        }
        topicHtml += "</ul></div>";
        topicsList.append(topicHtml);
    });
}

function getQuestions(id, callback){
    $.ajax({
        type: "GET",
        url: "rest/test/questions/" + id,
        success: function(data){
            callback(data)
        }
    })
}

function sendResult(){
    var result = JSON.stringify({
        subjectId: subjectId,
        name: userName,
        surname: userSurname,
        answers: answers
    });
    $.ajax({
        type: "POST",
        url: "rest/test/answers",
        //dataType: "json",
        contentType: "application/json",
        data: result,
        success: function(data){
            //ca?llback(data)
            alert("Success")
        }
    })
}


function readAnswer (data, index) {
    var answId = $('input[name="answer"]:checked').val();
    answers[index] = {subjectId: subjectId, answerId : answId, questionId : data[index].id};

}
function findAnswers(id) {
    for (var i = 0; i < answers.length; i++) {
        var ans = answers[i];
        if(ans.questionId == id){
            return ans.answerId;
        }
    }
}
function showQuestion(data, index) {
    var elem = data[index];
    if(index< data.length) {
        $("#testForm").empty();
        var fieldSet = $(document.createElement('fieldset'));
        $("#testForm").append(fieldSet);
        //console.log(fieldSet);
        var label = $(document.createElement('legend'));
        label.text(elem.question);
        fieldSet.append(label);
        fieldSet.addClass("question");
        fieldSet.addClass("active");
        var ansId = findAnswers(data[index].id);
        $.each(elem.answer, function (indexAnswer, elemAnswer) {
            var answerDiv = $(document.createElement('div'));
            //console.log(answerDiv);
            answerDiv.addClass("radio");
            var radioInput = $(document.createElement('input'));
            var radioLabel = $(document.createElement('label'));
            radioInput.attr("type", "radio");
            radioInput.attr("name", "answer");
            if(ansId == elemAnswer.answerId){
                radioInput.attr("checked", "checked");
            }
            radioInput.attr("value", elemAnswer.answerId);
            radioLabel.append(radioInput);
            radioLabel.append(elemAnswer.answer);
            answerDiv.append(radioLabel);
            fieldSet.append(answerDiv);
        });
    }
    $('input[name="answer"]:radio').change(function () {
        readAnswer(data, index)
    })

    if (index + 1 < data.length) {
        nextBtn.off("click")
        nextBtn.click(function () {
            //readAnswer(data, index);
            showQuestion(data, index + 1);
        });
        nextBtn.prop("disabled",false);
    } else {
        nextBtn.prop("disabled",true);
    }
    if (index - 1 >= 0) {
        prevBtn.off("click");
        prevBtn.click( function () {
            //readAnswer(data, index);
            showQuestion(data, index - 1);
        });

        prevBtn.prop("disabled",false);
    } else {
        prevBtn.prop("disabled",true);
    }
}
function createQuestionsQuiz(data){
    //todo: parse data and show
    alert(data);
    //$.each(data, function(index, elem){
    var index = 0;
    showQuestion(data, index);
    //})
    topicsBlock.hide(0);
    testBlock.show(0);
}

