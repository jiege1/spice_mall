// auth.middleware.ts
// 中间件职责：读取请求头Authorization，如果存在且有效的话，设置user对象到request中
import { Injectable, NestMiddleware } from '@nestjs/common';
import { Request, Response } from 'express';
import { UsersService } from 'src/modules/users/users.service';
import { User } from 'src/modules/users/entities/user.entity';

@Injectable()
export class AuthMiddleware implements NestMiddleware<Request|any, Response> {
  constructor(private readonly userService: UsersService) {}
  async use(request: Request | any, response: Response, next: () => void): Promise<void> {
    
    const token = request.header('authorization');
    console.log('AuthMiddleware token ', token);
    request.token = new User();
    // if(!token) {
    //   next();
    //   return;
    // }
    // const user = await this.userService.getUserByToken();
    // if(!user) {
    //   next();
    //   return;
    // }
    // request.token = token;
    next();
  }
}

export async function authMiddleware(
  request: Request | any, response: Response, next: () => void,
  ...others
): Promise<void> {
    
  const token = request.header('authorization');
  console.log('AuthMiddleware token ', token);
  console.log('authMiddleware this', others, this)
  if(!token) {
    next();
    return;
  }
  // const user = await this.userService.getUserByToken();
  // if(!user) {
  //   next();
  //   return;
  // }
  // request.token = token;
  // next();
}