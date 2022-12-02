package com.hongmai.clonfer.model.template;

/**
 * @author JiaweiWang
 * @date 2021/9/8
 * @description
 */
public class ResetPasswordTemplate {
    public static String resetPasswordTemplate(String username, String email, String uuid, String ip, String link) {
        String html = "<html lang=\"en\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" data-redeviation-bs-uid=\"45638\"><head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"x-apple-disable-message-reformatting\">\n" +
                "    <meta http-equiv=\"x-ua-compatible\" content=\"ie=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <meta name=\"format-detection\" content=\"telephone=no, date=no, address=no, email=no\">\n" +
                "    <!--[if mso]>\n" +
                "    <xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch></o:OfficeDocumentSettings></xml>\n" +
                "    <style>\n" +
                "      td,th,div,p,a,h1,h2,h3,h4,h5,h6 {font-family: \"Segoe UI\", sans-serif; mso-line-height-rule: exactly;}\n" +
                "    </style>\n" +
                "  <![endif]-->\n" +
                "    <title>密码重置</title>\n" +
                "    <link href=\"https://fonts.googleapis.com/css?family=Montserrat:ital,wght@0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,200;1,300;1,400;1,500;1,600;1,700\" rel=\"stylesheet\" media=\"screen\">\n" +
                "    <style>\n" +
                "      .hover-underline:hover {\n" +
                "        text-decoration: underline !important;\n" +
                "      }\n" +
                "\n" +
                "      @keyframes spin {\n" +
                "        to {\n" +
                "          transform: rotate(360deg);\n" +
                "        }\n" +
                "      }\n" +
                "\n" +
                "      @keyframes ping {\n" +
                "\n" +
                "        75%,\n" +
                "        100% {\n" +
                "          transform: scale(2);\n" +
                "          opacity: 0;\n" +
                "        }\n" +
                "      }\n" +
                "\n" +
                "      @keyframes pulse {\n" +
                "        50% {\n" +
                "          opacity: .5;\n" +
                "        }\n" +
                "      }\n" +
                "\n" +
                "      @keyframes bounce {\n" +
                "\n" +
                "        0%,\n" +
                "        100% {\n" +
                "          transform: translateY(-25%);\n" +
                "          animation-timing-function: cubic-bezier(0.8, 0, 1, 1);\n" +
                "        }\n" +
                "\n" +
                "        50% {\n" +
                "          transform: none;\n" +
                "          animation-timing-function: cubic-bezier(0, 0, 0.2, 1);\n" +
                "        }\n" +
                "      }\n" +
                "\n" +
                "      @media (max-width: 600px) {\n" +
                "        .sm-px-24 {\n" +
                "          padding-left: 24px !important;\n" +
                "          padding-right: 24px !important;\n" +
                "        }\n" +
                "\n" +
                "        .sm-py-32 {\n" +
                "          padding-top: 32px !important;\n" +
                "          padding-bottom: 32px !important;\n" +
                "        }\n" +
                "\n" +
                "        .sm-w-full {\n" +
                "          width: 100% !important;\n" +
                "        }\n" +
                "      }\n" +
                "    </style>\n" +
                "  <style class=\"redeviation-bs-style\" data-name=\"content\">/*! (c) Philipp König under GPL-3.0 */\n" +
                "body>div#redeviation-bs-indicator>div{opacity:0;pointer-events:none}body>iframe#redeviation-bs-sidebar.redeviation-bs-visible,body>iframe#redeviation-bs-overlay.redeviation-bs-visible{opacity:1;pointer-events:auto}body.redeviation-bs-noscroll{overflow:hidden !important}body>div#redeviation-bs-indicator>div{position:absolute;transform:translate3d(-40px, 0, 0);top:0;left:0;width:40px !important;height:100%;background:rgba(0,0,0,0.5);border-radius:0 10px 10px 0;transition:opacity 0.3s, transform 0.3s;z-index:2}body>div#redeviation-bs-indicator>div>span{-webkit-mask:no-repeat center/32px;-webkit-mask-image:url(chrome-extension://jdbnofccmhefkmjbkkdkfiicjkgofkdh/img/icon-bookmark.svg);background-color:#ffffff;position:absolute;display:block;top:0;left:0;width:100%;height:100%}body>div#redeviation-bs-indicator[data-pos='right']{left:auto;right:0}body>div#redeviation-bs-indicator[data-pos='right']>div{transform:translate3d(40px, 0, 0);left:auto;right:0;border-radius:10px 0 0 10px}body>div#redeviation-bs-indicator.redeviation-bs-fullHeight>div{border-radius:0}body>div#redeviation-bs-indicator.redeviation-bs-hover>div{transform:translate3d(0, 0, 0);opacity:1}body>div#redeviation-bs-indicator[data-pos='left'].redeviation-bs-has-lsb{height:100% !important;top:0 !important}body>div#redeviation-bs-indicator[data-pos='left'].redeviation-bs-has-lsb>div{background:transparent}body>div#redeviation-bs-indicator[data-pos='left'].redeviation-bs-has-lsb>div>span{-webkit-mask-position-y:20px}body>iframe#redeviation-bs-sidebar{width:350px;max-width:none;height:0;z-index:2147483646;background-color:rgba(0,0,0,0.6) !important;color-scheme:normal !important;speak:none;border:none;display:block !important;transform:translate3d(-350px, 0, 0);transition:width 0s 0.3s, height 0s 0.3s, opacity 0.3s, transform 0.3s}body>iframe#redeviation-bs-sidebar[data-pos='right']{left:auto;right:0;transform:translate3d(350px, 0, 0)}body>iframe#redeviation-bs-sidebar.redeviation-bs-visible{width:calc(100% + 350px);height:100%;transform:translate3d(0, 0, 0);transition:opacity 0.3s, transform 0.3s}body>iframe#redeviation-bs-sidebar.redeviation-bs-hideMask{background:none !important}body>iframe#redeviation-bs-sidebar.redeviation-bs-hideMask:not(.redeviation-bs-hover){width:calc(350px + 50px)}body>iframe#redeviation-bs-overlay{width:100%;max-width:none;height:100%;z-index:2147483647;border:none;speak:none;background:rgba(0,0,0,0.5) !important;transition:opacity 0.3s}\n" +
                "</style></head>\n" +
                "\n" +
                "  <body style=\"margin: 0; padding: 0; width: 100%; word-break: break-word; -webkit-font-smoothing: antialiased; --bg-opacity: 1; background-color: #eceff1; background-color: rgba(236, 239, 241, var(--bg-opacity));\" data-new-gr-c-s-check-loaded=\"14.1028.0\" data-gr-ext-installed=\"\">\n" +
                "    <div style=\"display: none;\">Clonf云展会 - 密码重置请求</div>\n" +
                "    <div role=\"article\" aria-roledescription=\"email\" aria-label=\"Reset your Password\" lang=\"en\">\n" +
                "      <table style=\"font-family: Montserrat, -apple-system, 'Segoe UI', sans-serif; width: 100%;\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "        <tbody><tr>\n" +
                "          <td align=\"center\" style=\"--bg-opacity: 1; background-color: #eceff1; background-color: rgba(236, 239, 241, var(--bg-opacity)); font-family: Montserrat, -apple-system, 'Segoe UI', sans-serif;\" bgcolor=\"rgba(236, 239, 241, var(--bg-opacity))\">\n" +
                "            <table class=\"sm-w-full\" style=\"font-family: 'Montserrat',Arial,sans-serif; width: 600px;\" width=\"600\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "              <tbody><tr>\n" +
                "                <td class=\"sm-py-32 sm-px-24\" style=\"font-family: Montserrat, -apple-system, 'Segoe UI', sans-serif; padding: 48px; text-align: center;\" align=\"center\">\n" +
                "                  \n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td align=\"center\" class=\"sm-px-24\" style=\"font-family: 'Montserrat',Arial,sans-serif;\">\n" +
                "                  <table style=\"font-family: 'Montserrat',Arial,sans-serif; width: 100%;\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "                    <tbody><tr>\n" +
                "                      <td class=\"sm-px-24\" style=\"--bg-opacity: 1; background-color: #ffffff; background-color: rgba(255, 255, 255, var(--bg-opacity)); border-radius: 4px; font-family: Montserrat, -apple-system, 'Segoe UI', sans-serif; font-size: 14px; line-height: 24px; padding: 48px; text-align: left; --text-opacity: 1; color: #626262; color: rgba(98, 98, 98, var(--text-opacity));\" bgcolor=\"rgba(255, 255, 255, var(--bg-opacity))\" align=\"left\">\n" +
                "                        <p style=\"font-weight: 600; font-size: 18px; margin-bottom: 0;\">你好</p>\n" +
                "                        <p style=\"font-weight: 700; font-size: 20px; margin-top: 0; --text-opacity: 1; color: #ff5850; color: rgba(255, 88, 80, var(--text-opacity));\">" + username + "</p>\n" +
                "                        <p style=\"margin: 0 0 24px;\">\n" +
                "                          您的账户\n" +
                "                          <a href=\"mailto:" + email + "\" class=\"hover-underline\" style=\"--text-opacity: 1; color: #7367f0; color: rgba(115, 103, 240, var(--text-opacity)); text-decoration: none;\">" + email + "</a>\n" +
                "                          (ID: " + uuid + ") 收到了一份来自 <span style=\"font-weight: 600;\">" + ip + "</span> 的密码重置请求。\n" +
                "                        </p>\n" +
                "                        <p style=\"margin: 0 0 24px;\">请使用以下链接完成密码重置并登陆。</p>\n" +
                "                        <a href=\"" + link + "\" style=\"display: block; font-size: 14px; line-height: 100%; margin-bottom: 24px; --text-opacity: 1; color: #7367f0; color: rgba(115, 103, 240, var(--text-opacity)); text-decoration: none;\">" + link + "</a>\n" +
                "                        <table style=\"font-family: 'Montserrat',Arial,sans-serif;\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "                          <tbody><tr>\n" +
                "                            <td style=\"mso-padding-alt: 16px 24px; --bg-opacity: 1; background-color: #7367f0; background-color: rgba(115, 103, 240, var(--bg-opacity)); border-radius: 4px; font-family: Montserrat, -apple-system, 'Segoe UI', sans-serif;\" bgcolor=\"rgba(115, 103, 240, var(--bg-opacity))\">\n" +
                "                              <a href=\"" + link + "\" style=\"display: block; font-weight: 600; font-size: 14px; line-height: 100%; padding: 16px 24px; --text-opacity: 1; color: #ffffff; color: rgba(255, 255, 255, var(--text-opacity)); text-decoration: none;\">重置密码 →</a>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody></table>\n" +
                "                        <p style=\"margin: 24px 0;\">\n" +
                "                          <span style=\"font-weight: 600;\">请注意：</span> 该连接的有效期为自申请提交起十分钟\n" +
                "                        </p>\n" +
                "                        <p style=\"margin: 0;\">\n" +
                "                          如果这不是您提出的请求，请忽略该邮件。\n" +
                "                        </p>\n" +
                "                        <table style=\"font-family: 'Montserrat',Arial,sans-serif; width: 100%;\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "                          <tbody><tr>\n" +
                "                            <td style=\"font-family: 'Montserrat',Arial,sans-serif; padding-top: 32px; padding-bottom: 32px;\">\n" +
                "                              <div style=\"--bg-opacity: 1; background-color: #eceff1; background-color: rgba(236, 239, 241, var(--bg-opacity)); height: 1px; line-height: 1px;\">&zwnj;</div>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody></table>\n" +
                "                        <p style=\"margin: 0 0 16px;\">此致, <br>Clonf云展会</p>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                      <td style=\"font-family: 'Montserrat',Arial,sans-serif; height: 20px;\" height=\"20\"></td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                      <td style=\"font-family: Montserrat, -apple-system, 'Segoe UI', sans-serif; font-size: 12px; padding-left: 48px; padding-right: 48px; --text-opacity: 1; color: #eceff1; color: rgba(236, 239, 241, var(--text-opacity));\">\n" +
                "                        \n" +
                "                        <p style=\"--text-opacity: 1; color: #263238; color: rgba(38, 50, 56, var(--text-opacity));\">\n" +
                "                          完成注册的同时我们默认您已阅读并认可\n" +
                "                          <a href=\"\" class=\"hover-underline\" style=\"--text-opacity: 1; color: #7367f0; color: rgba(115, 103, 240, var(--text-opacity)); text-decoration: none;\">用户协议</a> 和\n" +
                "                          <a href=\"\" class=\"hover-underline\" style=\"--text-opacity: 1; color: #7367f0; color: rgba(115, 103, 240, var(--text-opacity)); text-decoration: none;\">隐私协议</a>.\n" +
                "                        </p>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                      <td style=\"font-family: 'Montserrat',Arial,sans-serif; height: 16px;\" height=\"16\"></td>\n" +
                "                    </tr>\n" +
                "                  </tbody></table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </tbody></table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </tbody></table>\n" +
                "    </div>\n" +
                "  \n" +
                "\n" +
                "<iframe id=\"redeviation-bs-sidebar\" class=\"notranslate\" aria-hidden=\"true\" data-theme=\"default\" data-pos=\"left\"></iframe><div id=\"redeviation-bs-indicator\" data-theme=\"default\" class=\"redeviation-bs-fullHeight redeviation-bs-visible\" data-pos=\"left\" style=\"height: 100%; top: 0%;\"><div><span></span></div></div></body><grammarly-desktop-integration data-grammarly-shadow-root=\"true\"></grammarly-desktop-integration></html>";

        return html;
    }
}
