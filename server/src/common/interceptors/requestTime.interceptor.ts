import { Injectable, NestInterceptor, ExecutionContext, CallHandler } from '@nestjs/common';
import { Observable } from 'rxjs';
import { Request } from 'express';
import { tap } from 'rxjs/operators';

@Injectable()
export class RequestTimeInterceptor implements NestInterceptor {
  intercept(context: ExecutionContext, next: CallHandler): Observable<any> {
    const request = context.switchToHttp().getRequest() as Request;
    console.log(`${request.url} start ...`);
    const startTime = new Date();
    
    return next
      .handle()
      .pipe(
        tap(() => {
          console.log(`${request.url} response time:`, `${new Date().getTime() - startTime.getTime()} ms`);
        }),
      );
  }
}
