<?php get_header(); ?>

<?php while (have_posts()) : the_post(); ?>
  <article class="<?php post_class(); ?>" id="post-<?php the_ID(); ?>">
    <section class="entry-content">
      <?php the_content(); ?>
    </section>
  </article>
<?php endwhile; ?>

<!-- <div class="row">
  <div class="half-row">
    <h2>O nás</h2>
    Naše značková prodejna BLACK TAILOR se orientuje na jeansovou a sportovní módu vhodnou pro všechny typy postav a generace. Nabízíme jeansovou módu značek
    <strong>WRANGLER, LEE, H.I.S. </strong>a samozřejmě také doplňky od těchto výrobců, které zaručeně dopomohou k vylepšení Vaší image. Můžete si zvolit ze širokého
    sortimentu triček, mikin, svetrů ve velikostech S, M, L, XL, XXL, 3XL. Zaručeně si vyberete i ze sportovního oblečení outdoor, skate, snowboard značek
    <strong>HEAVY TOOLS.</strong>
  </div>
  <div class="half-row"><img src="wp-content/themes/MyTheme/img/Image-3.jpg" alt="img_shop1" /></div>
</div>
<div class="row">
  <div class="half-row">
    <h2>Naše služby</h2>
    <ul class="list">
      <li>Veškeré typy, velikosti, střihy, barvy a značky lze objednat přímo u nás v obchodě nebo telefonicky.</li>
      <li>Oděvy si můžete dát upravit přesně na míru u našich profesionálních krejčích zcela zdarma!!!</li>
      <li>Pro naše stálé zákazníky nabízíme výhodné zákaznické karty se slevami 5 % a 10 %.</li>
      <li>Zasílání zboží na dobírku po celé ČR.</li>
    </ul>
  </div>
  <div class="half-row"><img src="wp-content/themes/MyTheme/img/Image-5.jpg" alt="img_shop2" /></div>
</div>
<div class="cards">
  <br id="shop" />
  <h3>Naše produkty</h3>
  <div class="card">
    <div class="card-image">
      <a href="http://www.wrangler.com/" target="_blank" rel="noopener noreferrer"><img src="wp-content/themes/MyTheme/img/wrangler-logo.jpg" alt="jeans" /></a>
    </div>
  </div>
  <div class="card">
    <div class="card-image">
      <a href="http://www.his-jeans.company/" target="_blank" rel="noopener noreferrer"><img src="wp-content/themes/MyTheme/img/his-logo.jpg" alt="jeans" /></a>
    </div>
  </div>
  <div class="card">
    <div class="card-image">
      <a href="http://www.heavytools.cz/" target="_blank" rel="noopener noreferrer"><img src="wp-content/themes/MyTheme/img/HeavyTools.png" alt="jeans" /></a>
    </div>
  </div>
  <div class="card">
    <div class="card-image">
      <a href="https://www.lee.com/" target="_blank" rel="noopener noreferrer"><img src="wp-content/themes/MyTheme/img/Lee_logo.png" alt="jeans" /></a>
    </div>
  </div>
</div>
<div class="contact">
  <br id="kontakt" />
  <h3>Kontakt</h3>
  <div class="contact-info">
    <div class="cardc">
      <i class="card-icon far fa-envelope"></i>

      <a href="mailto: info@blacktailor.cz">info@blacktailor.cz</a>
    </div>
    <div class="cardc">
      <i class="card-icon fas fa-phone"></i>

      <a href="tel:+420776151559">+420 776&nbsp;151&nbsp;559</a>
    </div>
    <div class="cardc">
      <i class="card-icon fas fa-map-marker-alt"></i>

      Františka Křížka 403, 170 00 Letná
    </div>
  </div>
  <div class="open">
    <table class="content-table" style="height: 206px;" width="577">
      <thead>
        <tr>
          <th>Otevírací doba</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <tr class="active-row">
          <td>Pondělí</td>
          <td>9:30–18:00</td>
        </tr>
        <tr>
          <td>Úterý</td>
          <td>9:30–18:00</td>
        </tr>
        <tr class="active-row">
          <td>Středa</td>
          <td>9:30–18:00</td>
        </tr>
        <tr>
          <td>Čtvrtek</td>
          <td>9:30–18:00</td>
        </tr>
        <tr class="active-row">
          <td>Pátek</td>
          <td>9:30–18:00</td>
        </tr>
        <tr>
          <td>Sobota</td>
          <td>9:00–12:00</td>
        </tr>
      </tbody>
    </table>
  </div>
  <div class="map">
    <iframe
      id="map"
      src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2559.3280116896426!2d14.428623815934836!3d50.098867120516246!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x470b94c642826461%3A0x7322ff1d5d660426!2sBlack%20Tailor!5e0!3m2!1scs!2scz!4v1581262729554!5m2!1scs!2scz"
    ></iframe>
  </div>
</div>
-->
<?php get_sidebar(); ?>
<?php get_footer(); ?>