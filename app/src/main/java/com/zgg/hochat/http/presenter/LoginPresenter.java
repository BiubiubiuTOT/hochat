package com.zgg.hochat.http.presenter;

import com.zgg.hochat.base.BaseResult;
import com.zgg.hochat.bean.LoginInput;
import com.zgg.hochat.bean.LoginResult;
import com.zgg.hochat.bean.TokenResult;
import com.zgg.hochat.common.MyCallBack;
import com.zgg.hochat.http.contract.LoginContract;
import com.zgg.hochat.http.model.AccountModel;

import java.util.Map;

import retrofit2.Response;


/**
 * 类描述：地区
 * 创建人：liufei
 * 创建时间：2018/2/23 11:41
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class LoginPresenter extends LoginContract.Presenter {


    public LoginPresenter(LoginContract.View view, AccountModel model) {
        super(view, model);
    }

    @Override
    public void getToken(Map<String, Object> params) {
        model.getToken(params, new MyCallBack<TokenResult>() {

            @Override
            public void onSuc(Response<TokenResult> response) {
                if (isAttach) {
                    TokenResult body = response.body();
                    if (body.getCode() == 200) {
                        view.showToken(body);
                    } else view.showError("");
                }
            }

            @Override
            public void onFail(String message) {
                if (isAttach)
                    view.showError(message);
            }
        });


    }

    @Override
    public void login(LoginInput params) {
        model.login(params, new MyCallBack<BaseResult<LoginResult>>() {
            @Override
            public void onSuc(Response<BaseResult<LoginResult>> response) {
                if (isAttach) {
                    BaseResult<LoginResult> body = response.body();
                    int code = body.getCode();
                    if (code == 200) {
                        view.showLoginResult(body.getResult());
                    } else view.showError("登录失败：" + code);
                }
            }

            @Override
            public void onFail(String message) {
                if (isAttach)
                    view.showError(message);
            }
        });
    }
}
