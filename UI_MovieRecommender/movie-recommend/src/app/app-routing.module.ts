import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MovieListComponent } from './components/movie-list/movie-list.component';
import { MovieDetailComponent } from './components/movie-detail/movie-detail.component';
import { UserRecommendationComponent } from './components/user-recommendation/user-recommendation.component';
import { AdminComponent } from './components/admin/admin.component';
import { HomeComponent } from './components/home/home.component';

const routes: Routes = [
  { path: "", component:HomeComponent},
  { path: "movies", component:MovieListComponent},
  { path: "movies/:id", component:MovieDetailComponent},
  { path: "recommendations", component:UserRecommendationComponent},
  { path: "admin", component:AdminComponent},
  {path:"**", redirectTo:"movies"}

];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
