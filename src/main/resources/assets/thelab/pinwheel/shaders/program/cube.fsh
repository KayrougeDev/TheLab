#include veil:deferred_utils

uniform sampler2D DiffuseSampler0;
uniform sampler2D DiffuseDepthSampler;
//uniform vec2 iResolution;
uniform sampler2D Noise;
uniform float GameTime;

in vec2 texCoord;
out vec4 fragColor;

void main() {
  //vec2 uv = gl_FragCoord.xy / iResolution;
  vec2 center = vec2(0.5);
  float halfSizeX = 0.35;
  float halfSizeY = 0.2;
  //vec2 d = abs(uv - center);
  vec4 baseColor = texture(DiffuseSampler0, texCoord);

  float depthSample = texture(DiffuseDepthSampler, texCoord).r;
  float worldDepth = depthSampleToWorldDepth(depthSample);

  /**if (d.x <= halfSizeX && d.y <= halfSizeY) {
    //fragColor = baseColor / vec4(1.0, 0.5, 0.2, 0.85);
    //fragColor = (vec4(vec3(vec3(worldDepth) * 0.015)*1.3  , 1.0));
    float speed = GameTime * 1400;



  } else {
    fragColor = baseColor;
  }**/

  if(worldDepth > 650) {
    fragColor.rgb = 1.0 - vec4(vec3(vec3(worldDepth) * 0.015) * 1.3,1).rgb;
  }
  else {
    fragColor = vec4(vec3(vec3(worldDepth) * 0.015) * 1.4,1);
  }
}
