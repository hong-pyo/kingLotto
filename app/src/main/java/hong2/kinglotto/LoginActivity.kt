package hong2.kinglotto

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Base64.NO_WRAP
import android.util.Log
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.util.exception.KakaoException
import com.kakao.util.helper.Utility.getPackageInfo
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class LoginActivity : AppCompatActivity() {
    private val callback: SessionCallback = SessionCallback()
    companion object {
        private val TAG = LoginActivity::class.java!!.simpleName
        private val RC_RIGH_IN = 300
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        getHashKey(this)
        Session.getCurrentSession().addCallback(callback)
    }

    fun getHashKey(context: Context): String? {
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                val packageInfo = getPackageInfo(context, PackageManager.GET_SIGNING_CERTIFICATES)
                val signatures = packageInfo.signingInfo.apkContentsSigners
                val md = MessageDigest.getInstance("SHA")
                for (signature in signatures) {
                    md.update(signature.toByteArray())
                    return String(Base64.encode(md.digest(), NO_WRAP))
                }
            } else {
                val packageInfo =
                    getPackageInfo(context, PackageManager.GET_SIGNATURES) ?: return null

                for (signature in packageInfo!!.signatures) {
                    try {
                        val md = MessageDigest.getInstance("SHA")
                        md.update(signature.toByteArray())
                        return Base64.encodeToString(md.digest(), Base64.NO_WRAP)
                    } catch (e: NoSuchAlgorithmException) {
                        // ERROR LOG
                    }
                }
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return null
    }

    private class SessionCallback : ISessionCallback {
        override fun onSessionOpenFailed(exception: KakaoException?) {
            Log.e(TAG, "Session Call back :: onSessionOpenFailed: ${exception?.message}")
        }

        override fun onSessionOpened() {
            UserManagement.getInstance().me(object : MeV2ResponseCallback() {

                override fun onFailure(errorResult: ErrorResult?) {
                    Log.d(TAG,"Session Call back :: on failed ${errorResult?.errorMessage}")
                }

                override fun onSessionClosed(errorResult: ErrorResult?) {
                    Log.e(TAG,"Session Call back :: onSessionClosed ${errorResult?.errorMessage}")

                }

                override fun onSuccess(result: MeV2Response?) {
                    checkNotNull(result) { "session response null" }
                    Log.i(TAG, result.kakaoAccount.profile.nickname.toString() + " " + result.kakaoAccount.gender)
                }

            })
        }
    }
}
