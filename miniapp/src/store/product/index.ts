import { observable, action, computed } from "mobx";
import { ProductCategory, Product } from "src/api/interface/product";
import Base from "../base";

interface State {
  categoryId: number;
  categories: ProductCategory[];
  products: Product[];
}

export default class ProductModal extends Base<State> implements State {

  @observable categoryId = 1;
  @observable categories: ProductCategory[] = [
    {
      id: 1,
      title: '八角',
    },
    {
      id: 2,
      title: '茴香',
    },
    {
      id: 3,
      title: '香叶',
    },
  ];
  @observable products: Product[] = [
    {
      id: 1,
      title: '上等八角',
      price: 1600,
      indexImage: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1601221747283&di=f667db172f290f6f41c119f15d082598&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20180813%2F2ca56c86b8f34e2788b7e0dd0a762a4d.jpeg'
    },
    {
      id: 2,
      title: '一般八角',
      price: 1400,
      indexImage: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1601221747283&di=f667db172f290f6f41c119f15d082598&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20180813%2F2ca56c86b8f34e2788b7e0dd0a762a4d.jpeg'
    },
    {
      id: 3,
      title: '下等八角',
      price: 1200,
      indexImage: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1601221747283&di=f667db172f290f6f41c119f15d082598&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20180813%2F2ca56c86b8f34e2788b7e0dd0a762a4d.jpeg'
    },
    {
      id: 4,
      title: '一般香叶',
      price: 1050,
      indexImage: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1601221795360&di=c4201f54ce4f9a323d637b67655fb848&imgtype=0&src=http%3A%2F%2Fimg2.tbcdn.cn%2Ftfscom%2Fi4%2F417920244%2FTB29WxKvbBkpuFjy1zkXXbSpFXa_%2521%2521417920244.jpg'
    },
    {
      id: 5,
      title: '上等香叶',
      price: 990,
      indexImage: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1601221795360&di=c4201f54ce4f9a323d637b67655fb848&imgtype=0&src=http%3A%2F%2Fimg2.tbcdn.cn%2Ftfscom%2Fi4%2F417920244%2FTB29WxKvbBkpuFjy1zkXXbSpFXa_%2521%2521417920244.jpg'
    
    },
    {
      id: 6,
      title: '下等香叶',
      price: 890,
      indexImage: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1601221795360&di=c4201f54ce4f9a323d637b67655fb848&imgtype=0&src=http%3A%2F%2Fimg2.tbcdn.cn%2Ftfscom%2Fi4%2F417920244%2FTB29WxKvbBkpuFjy1zkXXbSpFXa_%2521%2521417920244.jpg'
    
    },
    {
      id: 7,
      title: '上等茴香',
      price: 800,
      indexImage: 'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2997071343,2114254869&fm=26&gp=0.jpg'
    
    },
    {
      id: 8,
      title: '一般茴香',
      price: 720,
      indexImage: 'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2997071343,2114254869&fm=26&gp=0.jpg'
    
    },
    {
      id: 9,
      title: '下等茴香',
      price: 610,
      indexImage: 'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2997071343,2114254869&fm=26&gp=0.jpg'
    },
  ];

  /**
   * 选中的商品
   */
  @computed get selectProducts() {
    return this.products.filter(item => item.categoryId === this.categoryId);
  }

  /**
   * 选中分类
   * @param categoryId 
   */
  // @action selectCategory(categoryId: number) {
  //   this.categoryId = categoryId;
  // }

  /**
   * 获取分类列表
   */
  @action async initData() {

  }

}
