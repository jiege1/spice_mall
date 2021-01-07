import { PagParams } from "src/common/utils/result/request";
import { Min } from "class-validator";

export class UserPagListQueryDto implements PagParams {

  @Min(1, { message: 'page 不能小于1' })
  page: number;

  @Min(1, { message: 'size 不能小于1' })
  size: number;

}
