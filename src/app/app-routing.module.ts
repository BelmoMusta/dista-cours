import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './components/home/home.component';


import {PostComponent} from './components/post/post.component';
import {LoginComponent} from './components/login/login.component';
import {AuthGuard} from './service/auth/auth.guard';
import {CreatePostComponent} from './components/post/create-post/create-post.component';
import {ShowPostComponent} from './components/post/show-post/show-post.component';
import {StudentComponent} from './components/student/student.component';
import {InscriptionComponent} from './components/inscription/inscription.component';
import {ActivationComponent} from './components/activation/activation.component';


const routes: Routes = [
  {path: '', component: HomeComponent, canActivate: [AuthGuard]},
  {path: 'posts', component: PostComponent, canActivate: [AuthGuard]},
  {path: 'student', component: StudentComponent, canActivate: [AuthGuard]},
  {path: 'inscription', component: InscriptionComponent},
  {path: 'activate', component: ActivationComponent},
  {path: 'login', component: LoginComponent},
  {path: 'posts/create', component: CreatePostComponent},
  {path: 'post/:id', component: ShowPostComponent},
  {path: '**', redirectTo: ''}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
