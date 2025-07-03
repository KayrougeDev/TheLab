#version 330
#include veil:space_helper
in vec2 texCoord;
out vec4 fragColor;
uniform sampler2D DiffuseSampler0;
uniform sampler2D DiffuseDepthSampler;
uniform float time;
uniform vec2 playerPos;

void main() {
    vec4 original = texture(DiffuseSampler0, texCoord);
    float depth = texture(DiffuseDepthSampler, texCoord).r;
    vec4 worldPos4 = screenToWorldSpace(texCoord, depth);
    vec3 worldPos = worldPos4.xyz;
    if(worldPos.x < 10.001 && worldPos.x > -10.001 && worldPos.z < 10.001 && worldPos.z > -10.001 && worldPos.y < 299.999) {
        fragColor = vec4(vec3((worldPos.y + 64.0) / 383.0), 1);
    }
    else {
        fragColor = original;
    }
}
