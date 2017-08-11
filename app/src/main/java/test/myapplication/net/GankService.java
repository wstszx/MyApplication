package test.myapplication.net;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import test.myapplication.model.GankResults;

/**
 * Created by wstszx on 2017/7/26.
 */

public interface GankService {
	@GET("data/{type}/{number}/{page}")
	Flowable<GankResults> getGankData(@Path("type") String type,
	                                  @Path("number") int pageSize,
	                                  @Path("page") int pageNum);
}
