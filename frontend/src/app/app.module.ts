import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UserDisplayComponent } from './user-display/user-display.component';
import { UserInputComponent } from './user-input/user-input.component';
import { UserEditComponent } from './user-edit/user-edit.component';
import { UserWrapperComponent } from './user-wrapper/user-wrapper.component';

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    UserDisplayComponent,
    UserInputComponent,
    UserEditComponent,
    UserWrapperComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
