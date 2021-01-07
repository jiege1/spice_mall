import { Controller, Post, UseGuards, Req } from '@nestjs/common';
import { AuthService } from './auth.service';
import { ApiTags, ApiOperation, ApiOkResponse, ApiQuery, ApiBody } from '@nestjs/swagger';
import { AuthGuard } from '@nestjs/passport';
import { Request } from 'express';
import { Result, OkResult } from 'src/common/utils/result/result';
import { okResultCreator } from 'src/common/utils/result/result.api';
import { User } from '../users/entities/user.entity';

@Controller('auth')
@ApiTags('授权登陆')
export class AuthController {

  constructor(private readonly authService: AuthService) {
  }

  @UseGuards(AuthGuard('local'))
  @Post('login')
  @ApiOperation({ summary: '用户账号密码登陆' })
  // @ApiBody({ })
  @ApiOkResponse({ schema: { example: okResultCreator('token') } })
  async login(@Req() req: Request): Promise<OkResult<string>> {
    return Result.ok(this.authService.login(req.user as User));
  }

}
