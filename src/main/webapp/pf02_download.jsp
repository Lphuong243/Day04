<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
<body>

<h1>Downloads</h1>
<h2>Paddlefoot - The Second CD</h2>

<table>
    <tr>
        <th>Song title</th>
        <th>Audio Format</th>
    </tr>
    <tr>
        <td>Sacramento Girl</td>
        <td>
            <a href="sound/${productCode}/sacramento_girl.mp3" download>MP3</a>
        </td>
    </tr>
    <tr>
        <td>64 Corvair</td>
        <td>
            <a href="sound/${productCode}/64_corvair.mp3" download>MP3</a>
        </td>
    </tr>
</table>

<p><a href="download?action=viewAlbums">View list of albums</a></p>
<p><a href="download?action=viewCookies">View all cookies</a></p>

</body>
</html>