// objects
var user;
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

$(function(){

    // login
    loginBlock = $('#loginBlock');
    firstNameInput = $('#firstName');
    lastNameInput = $('#lastName');
    startBtn = $('#start');
    startBtn.on('click', function(){
        if (firstNameInput.val() != "" && lastNameInput.val() != "") {
            user = firstNameInput.val() + " " + lastNameInput.val();
            loginBlock.hide(0);
            topicsBlock.show(0);
        }
    });

    // topics
    topicsJson = [
        {
            "id": "1",
            "name": "Topic 1",
            "tests": [
                { "id": 1, "name": "test 1" },
                { "id": 2, "name": "test 2" },
                { "id": 3, "name": "test 3" }
            ]
        },
        {
            "id": "2",
            "name": "Topic 2",
            "tests": [
                { "id": 4, "name": "test 4" },
                { "id": 5, "name": "test 5" },
                { "id": 6, "name": "test 6" }
            ]
        },
        {
            "id": "3",
            "name": "Topic 3",
            "tests": [
                { "id": 7, "name": "test 7" },
                { "id": 8, "name": "test 8" },
                { "id": 9, "name": "test 9" }
            ]
        },
        {
            "id": "4",
            "name": "Topic 4",
            "tests": [
                { "id": 10, "name": "test 10" },
                { "id": 11, "name": "test 11" },
                { "id": 12, "name": "test 12" }
            ]
        }
    ];
    topicsBlock = $('#topicsBlock');
    topicsList = $('#topicsList');
    $.each(topicsJson, function(index, elem){
        var topicHtml = "<li>" + elem.name + "<ul>";
        $.each(elem.tests, function(index, elem){
            topicHtml += '<li class="chooseTest" data-test="' + elem.id +'">' + elem.name +'</li>'
        })
        topicHtml += "<ul></li>";
        topicsList.append(topicHtml);
    });
    topicsList.on('click', '.chooseTest', function(){
        var testId = $(this).data('test');
        getTest(testId);
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