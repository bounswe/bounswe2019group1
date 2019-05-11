/*
Author       : Tech Trek.
Template Name: Bitfonix - Bitcoin And Crypto Currency HTML Template
Version      : 1.0
*/

/*=============================================
TABLE OF CONTENTS
================================================

1. PRELOADER JS
2. JQUERY STICKY MENU
3. MENU JS
4. SECTIONS BACKGROUNDS
5. HOME SLIDER JS
6. SERVICE SLIDER JS
7. TEAM SLIDER JS
8. TESTIMONIAL SLIDER JS
9. CLIENT SLIDER JS
10. SINGLE BLOG SLIDER JS
11. ACCORDION JS
12. COUNTDOWN JS
13. VENOBOX JS
14. MASONRY JS
15. WOW ANIMATION JS

Table Of Contents end
================================================
*/

(function ($) {
	'use strict';

	jQuery(document).on('ready', function () {


		/* 1. PRELOADER JS */

		$(window).on('load', function () {
			function fadeOut(el) {
				el.style.opacity = 0.4;
				var last;
				var tick = function () {
					el.style.opacity = +el.style.opacity - (new Date() - last) / 600;
					last = +new Date();
					if (+el.style.opacity > 0) {
						(window.requestAnimationFrame && requestAnimationFrame(tick)) || setTimeout(tick, 100);
					} else {
						el.style.display = "none";
					}
				};
				tick();
			}
			var pagePreloaderId = document.getElementById("page-preloader");
			setTimeout(function () {
				fadeOut(pagePreloaderId)
			}, 1000);
		});


		/* 2. JQUERY STICKY MENU */

		$(".sticky-menu").sticky({
			topSpacing: 0
		});

		/* 3. MENU JS */

		$('nav.navbar').meanmenu({
			meanMenuContainer: '.mainmenu-area',
			meanScreenWidth: "991"
		});

		$(window).on('scroll', function () {
			if ($(this).scrollTop() > 200) {
				$('.mainmenu-area').addClass('menu-animation');
				$('.topcontrol').addClass('topanimation');
			} else {
				$('.mainmenu-area').removeClass('menu-animation');
				$('.topcontrol').removeClass('topanimation');
			}
		});
		
        $('a.js-scroll-trigger').on('click', function(e) {
            var anchor = $(this);
            $('html, body').stop().animate({
                scrollTop: $(anchor.attr('href')).offset().top - 48
            }, 1000);
            e.preventDefault();
        });
		
        // Activate scrollspy to add active class to navbar items on scroll
        $('body.home-4').scrollspy({
            target: '.mainmenu-area',
            offset: 200
        });


		/* 4. SECTIONS BACKGROUNDS */

		var pageSection = $("section,div");
		pageSection.each(function (indx) {

			if ($(this).attr("data-background")) {
				$(this).css("background-image", "url(" + $(this).data("background") + ")");
			}
		});


		/* 5. HOME SLIDER JS */

		$('.home-slides').owlCarousel({
			items: 1,
			loop: true,
			autoplay: false,
			autoplayTimeout: 4000,
			animateIn: "fadeInLeft",
			animateOut: "fadeOutRight",
			dots: true,
			nav: true,
			navText: ["<i class='icofont icofont-rounded-left'></i>", "<i class='icofont icofont-rounded-right'></i>"],
			responsiveClass: true,
			responsive: {
				0: {
					nav: false,
					dots: true
				},
				600: {
					nav: false,
					dots: true
				},
				768: {
					nav: true
				},
				1000: {
					nav: true
				}
			}
		});

		$('.home-slides-2').owlCarousel({
			items: 1,
			loop: true,
			autoplay: false,
			autoplayTimeout: 4000,
			animateIn: "fadeInLeft",
			animateOut: "fadeOutRight",
			dots: false,
			nav: true,
			navText: ["<i class='icofont icofont-rounded-left'></i>", "<i class='icofont icofont-rounded-right'></i>"],
			responsiveClass: true,
			responsive: {
				0: {
					nav: false,
					dots: true
				},
				600: {
					nav: false,
					dots: true
				},
				768: {
					nav: true
				},
				1000: {
					nav: true
				}
			}
		});

		$(".home-slides,.home-slides-2").on("translate.owl.carousel", function () {
			$(this).find(".owl-item .home-single-slide h2").removeClass("fadeInLeft animated").css("opacity","0");
			$(this).find(".owl-item .home-single-slide p").removeClass("fadeInRight animated").css("opacity","0");
			$(this).find(".owl-item .home-single-slide .home-single-slide-button a").removeClass("fadeInUp animated").css("opacity","0");
		});
		
		$(".home-slides,.home-slides-2").on("translated.owl.carousel", function () {
			$(this).find(".owl-item .home-single-slide h2").addClass("fadeInLeft animated").css("opacity","1");
			$(this).find(".owl-item .home-single-slide p").addClass("fadeInRight animated").css("opacity","1");
			$(this).find(".owl-item .home-single-slide .home-single-slide-button a").addClass("fadeInUp animated").css("opacity","1");
		});

		
		/* 6. SERVICE SLIDER JS */

		$('.services-slider').owlCarousel({
			margin: 30,
			loop: true,
			autoplay: false,
			autoplayTimeout: 4000,
			navSpeed: 700,
			dotsSpeed: 700,
			dragEndSpeed: 700,
			dots: false,
			nav: true,
			navText: ["<i class='icofont icofont-rounded-left'></i>", "<i class='icofont icofont-rounded-right'></i>"],
			responsiveClass: true,
			responsive: {
				0: {
					items: 1
				},
				600: {
					items: 2
				},
				768: {
					items: 2
				},
				1000: {
					items: 3
				}
			}
		});


		/* 7. TEAM SLIDER JS */

		$('.team-slider').owlCarousel({
			margin: 30,
			loop: true,
			autoplay: true,
			autoplayTimeout: 4000,
			navSpeed: 700,
			dotsSpeed: 700,
			dragEndSpeed: 700,
			dots: false,
			nav: false,
			responsiveClass: true,
			responsive: {
				0: {
					items: 1,
					dots: true
				},
				600: {
					items: 2,
					dots: true
				},
				768: {
					items: 3
				},
				1000: {
					items: 4
				}
			}
		});


		/* 8. TESTIMONIAL SLIDER JS */

		$('.testimonial-slider').owlCarousel({
			margin: 0,
			items: 1,
			loop: true,
			autoplay: false,
			autoplayTimeout: 4000,
			navSpeed: 700,
			dotsSpeed: 700,
			dragEndSpeed: 700,
			animateIn: "fadeInDown",
			animateOut: "fadeOutDown",
			dots: false,
			nav: true,
			navText: ["<i class='icofont icofont-thin-left'></i>", "<i class='icofont icofont-thin-right'></i>"]
		});


		/* 9. CLIENT SLIDER JS */

		$('.client-slider').owlCarousel({
			margin: 30,
			loop: true,
			autoplay: true,
			autoplayTimeout: 4000,
			dragEndSpeed: 700,
			dots: false,
			nav: false,
			responsiveClass: true,
			responsive: {
				0: {
					items: 1
				},
				600: {
					items: 3
				},
				768: {
					items: 5
				},
				1000: {
					items: 5
				}
			}
		});


		/* 10. SINGLE BLOG SLIDER JS */

		$('.single-blog-slider').owlCarousel({
			margin: 0,
			items: 1,
			loop: true,
			autoplay: false,
			autoplayTimeout: 4000,
			navSpeed: 700,
			dotsSpeed: 700,
			dragEndSpeed: 700,
			dots: false,
			nav: true,
			navText: ["<i class='icofont icofont-thin-left'></i>", "<i class='icofont icofont-thin-right'></i>"]
		});


		/* 11. ACCORDION JS */

		$(".accordion-box").on('click', '.acc-btn', function () {
			var outerBox = $(this).parents('.accordion-box');
			var target = $(this).parents('.accordion');
			if ($(this).hasClass('active') !== true) {
				$('.accordion .acc-btn').removeClass('active');
			}
			if ($(this).next('.acc-content').is(':visible')) {
				return false;
			} else {
				$(this).addClass('active');
				$(outerBox).children('.accordion').removeClass('active-block');
				$(outerBox).find('.accordion').children('.acc-content').slideUp(300);
				target.addClass('active-block');
				$(this).next('.acc-content').slideDown(300);
			}
		});


		/* 12. COUNTDOWN JS */

		$('.counter-section').on('inview', function (event, visible, visiblePartX, visiblePartY) {
			if (visible) {
				$(this).find('.timer').each(function () {
					var $this = $(this);
					$({
						Counter: 0
					}).animate({
						Counter: $this.text()
					}, {
						duration: 2000,
						easing: 'swing',
						step: function () {
							$this.text(Math.ceil(this.Counter));
						}
					});
				});
				$(this).unbind('inview');
			}
		});


		/* 13. VENOBOX JS */

		$('.venobox').venobox({
			framewidth: '600px',
			frameheight: '400px',
			numeratio: true,
			titleattr: 'data-title',
			spinner: 'wandering-cubes',
			numerationPosition: 'bottom',
			titlePosition: 'bottom',
			titleColor: '#f7921a',
			spinColor: '#fff'
		});


		/* 14. MASONRY JS */

		$('.grid').masonry({
			// options
			itemSelector: '.grid-item',
			columnWidth: 200
		});


	});


	/* 15. WOW ANIMATION JS */

	new WOW().init();


})(jQuery);