import { Component } from '@angular/core';
import {HttpClient ,HttpHeaders} from '@angular/common/http';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'projcalc';


constructor(private http:HttpClient){}
x:string="";
b:any;
  display(y:string){
    this.x=this.x+y
    
  }
  
  send(y:string){
    this.http.get('http://localhost:8080/print/Welcome',{
      responseType: 'text', 
    params:{
      expression :y
    },
    observe: 'response'
    
    }).subscribe(response =>{
      this.b=response.body
      console.log(this.b )})
      }
     
  get_answer(y:string){
        this.x=this.x+y
        this.send(this.x)
        if(this.b ){
         //remove the previous expression 
          this.clearall()
          this.x=this.b+y
          
          }
        
  } 

  aft:any
  clearall(){
      this.aft=this.x.slice(-1,0);
      this.x=this.aft;
  }
 after:any
  delete(){
    this.after=this.x.slice(0,-1);
    this.x=this.after;
  }    
}
