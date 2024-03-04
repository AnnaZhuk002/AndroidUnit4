package com.example.annacityapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.annacityapp.data.ReccomndationsData

enum class MyCityAppScreen(val route: String) {
    Categories("categories"),
    Recommendations("recommendations/{category}"),
    RecommendationDetail("recommendation_detail/{recommendationId}")
}

@Composable
fun AnnaCityApp(
    windowSize: WindowWidthSizeClass,
    onBackPressed: () -> Unit,
) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = MyCityAppScreen.Categories.route) {
        composable(MyCityAppScreen.Categories.route) {
            CategoryListScreen(
                onCategorySelected = { category ->
                    navController.navigate(
                        MyCityAppScreen.Recommendations.route.replace(
                            "{category}",
                            category
                        )
                    )
                }
            )
        }

        composable(
            MyCityAppScreen.Recommendations.route,
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category")
            if (category != null) {
                RecommendationListScreen(
                    category = category,
                    onRecommendationSelected = { recommendationId ->
                        navController.navigate(
                            MyCityAppScreen.RecommendationDetail.route.replace(
                                "{recommendationId}",
                                recommendationId
                            )
                        )
                    }
                )
            }
        }

        composable(
            MyCityAppScreen.RecommendationDetail.route,
            arguments = listOf(navArgument("recommendationId") { type = NavType.StringType })
        ) { backStackEntry ->
            val recommendationId = backStackEntry.arguments?.getString("recommendationId")
            if (recommendationId != null) {
                RecommendationDetailScreen(
                    recommendationId = recommendationId,
                    onNavigateUp = { navController.popBackStack() }
                )
            }
        }
    }
}

@Composable
fun CategoryListScreen(onCategorySelected: (String) -> Unit) {
    val categories = ReccomndationsData.recommendations
        .map { it.category }
        .distinct()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            Text(
                text = "Категории",
                modifier = Modifier
                    .background(Color(255, 255, 125))
                    .fillMaxWidth()
                    .padding(16.dp),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            LazyColumn {
                items(categories) { category ->
                    Text(
                        text = stringResource(category),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onCategorySelected(category.toString()) }
                            .padding(16.dp),
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

@Composable
fun RecommendationListScreen(
    category: String,
    onRecommendationSelected: (String) -> Unit
) {
    val recommendations = ReccomndationsData.recommendations
        .filter { it.category.toString() == category }

    Column {
        Text(
            text = "Места",
            modifier = Modifier
                .background(Color(255, 255, 125))
                .fillMaxWidth()
                .padding(16.dp),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        LazyColumn {
            itemsIndexed(recommendations) { _, recommendation ->
                Text(
                    text = stringResource(recommendation.name),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onRecommendationSelected(recommendation.id.toString()) }
                        .padding(16.dp),
                    fontSize = 18.sp
                )
            }
        }
    }

}

@Composable
fun RecommendationDetailScreen(
    recommendationId: String,
    onNavigateUp: () -> Unit
) {
    val recommendation = ReccomndationsData.recommendations
        .firstOrNull { it.id.toString() == recommendationId }

    if (recommendation != null) {
        Column {
            Text(
                text = "Рекомендация",
                modifier = Modifier
                    .background(Color(255, 255, 125))
                    .fillMaxWidth()
                    .padding(16.dp),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = stringResource(recommendation.name),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = stringResource(recommendation.category), fontSize = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(recommendation.address),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = stringResource(recommendation.description), fontSize = 16.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(id = recommendation.imageResourceId),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    } else {
        Text("Not Found.")
    }
}


@Preview
@Composable
fun CategoryListScreenPreview() {
    CategoryListScreen {}
}

@Preview
@Composable
fun RecommendationListScreenPreview() {
    RecommendationListScreen("Кофейни") {
    }
}

@Preview
@Composable
fun RecommendationDetailScreenPreview() {
    RecommendationDetailScreen("1") {}
}