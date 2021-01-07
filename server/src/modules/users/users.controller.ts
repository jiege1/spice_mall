import { Controller, Post, Body, Get, UseGuards, Query, Req, Param, Headers } from '@nestjs/common';
import { ApiTags, ApiOperation, ApiResponse, ApiResponseProperty, ApiOkResponse, ApiProperty } from '@nestjs/swagger';
import { Request } from 'express';
// import { AuthGuard } from '@nestjs/passport';
import { Result, OkResult, ListResult } from 'src/common/utils/result/result';
import { UsersService } from './users.service';
import { User, UserRoleEnum } from './entities/user.entity';
import { CreateUserDto } from './dto/createUser.dto';
import { IncomingHttpHeaders } from 'http';
import { AuthGuard } from '@nestjs/passport';
// import { AuthRoles, AuthGuard } from 'src/common/guards/auth.guards';
// import { ApiCreateResult } from 'src/common/utils/result/apiResult';
// import { createResultCreator, listResultCreator } from 'src/common/utils/result/apiResult';
// import { ApiCreateResult } from 'src/common/utils/result/apiResult';

@Controller('user')
@ApiTags('用户管理')
export class UsersController {

  constructor(
    private readonly usersService: UsersService,
  ) {}

  /**
   * 获取个人信息
   * @param res 
   */
  @Get('/profile')
  @UseGuards(AuthGuard('jwt'))
  async getUserProfile(@Req() res: Request): Promise<OkResult<User>> {
    const user = res.user as User;
    return Result.ok(user);
  }

  // /**
  //  * 获取用户列表
  //  */
  // @Get('')
  // @ApiOperation({ summary: '查询所有用户' })
  // // @ApiOkResponse({ schema: { example: listResultCreator<User>(new User()) } })
  // @AuthRoles(UserRoleEnum.SuperAdmin, UserRoleEnum.User)
  // async findAll(): Promise<ListResult<User>> {
  //   // await Utils.sleep(3000);
  //   return Result.list(await this.usersService.findUserAll());
  // }

}
