import {
  Injectable,
  NestInterceptor,
  ExecutionContext,
  CallHandler,
} from '@nestjs/common';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable()
export class NotFoundInterceptor implements NestInterceptor<any, any> {
  intercept(context: ExecutionContext, next: CallHandler): Observable<any> {
    // stream$ is an Observable of the controller's result value
    return next.handle()
      .pipe(tap(data => {
        console.log('data ===>', data);
        // if (data === undefined) throw new NotFoundException();
      }));
  }
}