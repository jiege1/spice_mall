import { ApiOperation, ApiProperty } from "@nestjs/swagger";
import { SuccessCode } from "./result.code";
import { ErrorCode, OkResult, ListResult, PagListResult } from "./result";
import { Type } from "@nestjs/common";

export function okResultCreator<D>(data: D): OkResult<D> {
  return {
    code: SuccessCode.Success,
    msg: '',
    data,
  };
}

// export function createResultCreator(): OkResult<{ id: number }> {
//   return {
//     code: SuccessCode.Success,
//     msg: '',
//     data: { id: 1 },
//   };
// }

// export function listResultCreator<D>(item: D): ListResult<D> {

//   console.log('item====>', item);
//   return {
//     code: SuccessCode.Success,
//     msg: '',
//     data: {
//       list: [item],
//     },
//   };
// }

// export function pagListResultCreator<D>(list: D[], total: number): PagListResult<D> {
//   return {
//     code: SuccessCode.Success,
//     msg: '',
//     data: {
//       list,
//       total,
//     },
//   };
// }

export class ApiResult<D> implements OkResult<D> {

  @ApiProperty({ description: '错误码为0' })
  code = SuccessCode.Success;

  @ApiProperty({ description: '错误信息为空' })
  msg = '';

  data: D;

}

class ApiCreate {

  @ApiProperty({ description: '新建的ID', type: {id: Number} })
  data = { id: 0 };

}

export class ApiCreateResult extends ApiResult<{ id: number }> {
    
  @ApiProperty({ description: '新建的ID', type: {id: Number} })
  data = { id: 0 };
  
}

class ApiList<I> {

  @ApiProperty({ description: '列表' })
  list: I[];

}

export class ApiListResult<I> extends ApiResult<ApiList<I>> {
    
  @ApiProperty({ description: '数据', type: ApiList })
  data: ApiList<I>;

}

class ApiPagList<I> extends ApiList<I> {

  @ApiProperty({ description: '总数' })
  total: number;

}

export class ApiPagListResult<I> extends ApiResult<ApiPagList<I>> {
    
  @ApiProperty({ description: '数据', type: ApiPagList })
  data: ApiPagList<I>;

}
