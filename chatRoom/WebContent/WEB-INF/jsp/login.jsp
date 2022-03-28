<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title></title>
  </head>
  <body>
    <h1>既存のアカウントでログイン</h1>
    <form class="" action="/chatRoom/login" method="post">
      <ul class="loginInput">
        <li>ID:<input type="text" name="id" value=""></li>
        <li>Password:<input type="text" name="pass" value=""></li>
        <li><input type="submit" name="" value="ログイン"></li>
      </ul>

    </form>
  </body>
  <footer>
    <ul>
      <li><a href="/chatRoom/regAccount">アカウント新規作成</a></li>
      <li><a href="/chatRoom/index">TOPへ戻る</a></li>
    </ul>
  </footer>
</html>
