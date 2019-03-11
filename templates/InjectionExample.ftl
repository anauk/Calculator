<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>Hello, ${name1}!</h1>
    <h1>Hello, ${name2}!</h1>

    <ul>
    <#list dataForList as listItem>
        <li>${listItem}</li>
    </#list>
    </ul>

    <ol>
    <#list data3 as listItem>
        <li>${listItem.id}: ${listItem.name1}: ${listItem.coolPresentation()}</li>
    </#list>
    </ol>

    <#if questions??>
        ${questions}
    <#else>
        there are no more questions
    </#if>
</body>
</html>