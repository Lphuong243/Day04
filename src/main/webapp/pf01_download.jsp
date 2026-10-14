<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
<body>

<h1>Downloads</h1>
<h2>Paddlefoot - The First CD</h2>

<table>
    <tr>
        <th>Song title</th>
        <th>Audio Format</th>
    </tr>
    <tr>
        <td>Neon Lights</td>
        <td>
            <a href="sound/${productCode}/neon_lights.mp3" download>MP3</a>
        </td>
    </tr>
    <tr>
        <td>Most of the Time</td>
        <td>
            <a href="sound/${productCode}/most_of_the_time.mp3" download>MP3</a>
        </td>
    </tr>
    <tr>
        <td>Counting Out Loud</td>
        <td>
            <a href="sound/${productCode}/counting_out_loud.mp3" download>MP3</a>
        </td>
    </tr>
</table>

<p><a href="download?action=viewAlbums">View list of albums</a></p>
<p><a href="download?action=viewCookies">View all cookies</a></p>

</body>
</html>