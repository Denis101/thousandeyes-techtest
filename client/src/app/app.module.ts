import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';

import { PersonService } from './service/person.service';
import { AppComponent } from './app.component';
import { HomepageComponent } from './homepage/homepage.component';
import { FollowersComponent } from './followers/followers.component';
import { FollowingComponent } from './following/following.component';
import { MessagesComponent } from './messages/messages.component';
import { LoginComponent } from './login/login.component';
import { AuthService } from './service/auth.service';
import { GraphComponent } from './graph/graph.component';
import { NodeComponent } from './graph/node/node.component';
import { LinkComponent } from './graph/link/link.component';
import { D3Service } from './service/d3.service';
import { DraggableDirective } from './directive/draggable.directive';
import { ZoomableDirective } from './directive/zoom.directive';

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    FollowersComponent,
    FollowingComponent,
    MessagesComponent,
    LoginComponent,
    GraphComponent,
    NodeComponent,
    LinkComponent,
    DraggableDirective,
    ZoomableDirective
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    HttpClient,
    AuthService,
    PersonService,
    D3Service
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
