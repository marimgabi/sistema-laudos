import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  standalone: true, 
  imports: [
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule
  ],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'] 
})
export class LoginComponent {
  email: string = '';
  password: string = '';
  private snackBar = inject(MatSnackBar);

  constructor(private http: HttpClient, private router: Router, private authService: AuthService) {}

  onLogin() {
    const body = { email: this.email, password: this.password };
  
    this.http.post<any>(`${environment.apiUrl}/auth/login`, body).subscribe({
      next: (res) => {
        this.authService.login(res.token, res.user);
        this.router.navigate(['/laudos']);
      },
      error: (err) => {
        const msg = err.status === 401
          ? 'E-mail ou senha inválidos.'
          : 'Erro ao realizar login. Tente novamente mais tarde.';
        
        this.snackBar.open(msg, 'Fechar', { duration: 4000 });
      }
    });
  }
}
