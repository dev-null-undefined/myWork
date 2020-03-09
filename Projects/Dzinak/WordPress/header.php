<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title><?php echo get_bloginfo( $show, 'display' )?></title>
  <link rel="stylesheet" href="<?php echo get_template_directory_uri(); ?>/style.css" type="text/css" media="all" />
  <script src="https://kit.fontawesome.com/a076d05399.js"></script>
  <?php wp_head();?>
</head>

<body>
  <div class="menu">
    <nav>
      <input type="checkbox" id="checkmenu">
      <label for="checkmenu" class="btncheck">
        <i class="fas fa-bars"></i>
      </label>
      <label class="logo"><img src="<?php echo get_template_directory_uri() ?>/img/logo.jpg" alt="Black_Tailor"></label>
      <ul>
        <li><a class="active" href="#">O nás</a></li>
        <li><a class="active" href="#shop">Zboží</a></li>
        <li><a class="active" href="#kontakt">Kontakt</a></li>
      </ul>
    </nav>
  </div>
  <div class="container">