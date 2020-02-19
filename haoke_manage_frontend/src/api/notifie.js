import request from "../common/request";

export async function queryNotices() {
  return request("/api/notices");
}
