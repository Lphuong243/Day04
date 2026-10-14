<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
<body>

<h1>Downloads</h1>
<h2>86 (the band) - True Life Songs and Pictures</h2>

<table>
    <tr>
        <th>Song title</th>
        <th>Audio Format</th>
    </tr>
    <tr>
        <td>You Are a Star</td>
        <td>
            <a href="sound/${productCode}/star.mp3" download>MP3</a>
        </td>
    </tr>
    <tr>
        <td>Don't Make No Difference</td>
        <td>
            <a href="sound/${productCode}/no_difference.mp3" download>MP3</a>
        </td>
    </tr>
</table>

<p><a href="download?action=viewAlbums">View list of albums</a></p>
<p><a href="download?action=viewCookies">View all cookies</a></p>

</body>
</html>