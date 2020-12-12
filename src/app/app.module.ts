import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {OwlModule} from 'ngx-owl-carousel';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';

import {NavbarComponent} from './components/navbar/navbar.component';
import {HomeComponent} from './components/home/home.component';
import {FooterComponent} from './components/footer/footer.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {PostComponent} from './components/post/post.component';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
import {LoginComponent} from './components/login/login.component';
import {ErrorInterceptor} from './service/basic.error.interceptor';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CreatePostComponent} from './components/post/create-post/create-post.component';
import {ShowPostComponent} from './components/post/show-post/show-post.component';
import {TableElementComponent} from './components/table-element/table-element.component';
import {PostCardComponent} from './components/post/post-card/post-card.component';
import {TagComponent} from './components/tag/tag.component';
import {JwtInterceptor} from './service/auth/jwt.interceptor';
import {StudentComponent} from './components/student/student.component';
import {InscriptionComponent} from './components/inscription/inscription.component';
import {ActivationComponent} from "./components/activation/activation.component";


@NgModule({
  declarations: [
    AppComponent,

    NavbarComponent,
    HomeComponent,
    InscriptionComponent,
    ActivationComponent,
    StudentComponent,
    FooterComponent,
    PostComponent,
    LoginComponent,
    CreatePostComponent,
    ShowPostComponent,
    TableElementComponent,
    PostCardComponent,
    TagComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    FormsModule,
    OwlModule,
    HttpClientModule

  ],
  providers: [HttpClient, {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
