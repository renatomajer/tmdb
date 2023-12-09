package agency.five.tmdb.util

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavBackStackEntry

private const val ANIMATION_DURATION = 300
private const val ANIMATION_DELAY = 350


// Root Navigation Graph
fun mainScreenExitTransition(): AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition =
    {
        fadeOut(animationSpec = tween(delayMillis = ANIMATION_DELAY))
    }

fun detailsScreenEnterTransition(): AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition =
    {
        slideInVertically(
            animationSpec = tween(ANIMATION_DURATION),
            initialOffsetY = { fullHeight -> fullHeight }
        ) + fadeIn(animationSpec = tween(ANIMATION_DURATION))
    }

fun detailsScreenPopExitTransition(): AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition =
    {
        slideOutHorizontally(
            animationSpec = tween(ANIMATION_DURATION),
            targetOffsetX = { fullWidth -> fullWidth }
        ) + fadeOut(animationSpec = tween(ANIMATION_DURATION))
    }

// Bottom Bar Navigation Graph
fun homeScreenExitTransition(): AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition =
    {
        slideOutHorizontally(
            targetOffsetX = { fullWidth -> -fullWidth },
            animationSpec = tween(ANIMATION_DURATION)
        ) + fadeOut(animationSpec = tween(ANIMATION_DURATION))
    }

fun homeScreenPopEnterTransition(): AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition =
    {
        slideInHorizontally(
            initialOffsetX = { fullWidth -> -fullWidth },
            animationSpec = tween(ANIMATION_DURATION)
        ) + fadeIn(animationSpec = tween(ANIMATION_DURATION))
    }

fun favoritesScreenEnterTransition(): AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition =
    {
        slideInHorizontally(
            initialOffsetX = { fullWidth -> fullWidth },
            animationSpec = tween(ANIMATION_DURATION)
        ) + fadeIn(animationSpec = tween(ANIMATION_DURATION))
    }

fun favoritesScreenPopExitTransition(): AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition =
    {
        slideOutHorizontally(
            targetOffsetX = { fullWidth -> fullWidth },
            animationSpec = tween(ANIMATION_DURATION)
        ) + fadeOut(animationSpec = tween(ANIMATION_DURATION))
    }