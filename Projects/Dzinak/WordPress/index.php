<?php get_header(); ?>

<?php while ( have_posts() ) : the_post(); ?>
  <article class="<?php post_class(); ?>" id="post-<?php the_ID(); ?>">
    <section class="entry-content">
      <?php the_content(); ?>
    </section>
  </article>
  <?php endwhile; ?>
<?php get_sidebar(); ?>
<?php get_footer(); ?>


